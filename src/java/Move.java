public class Move extends Board{
    private String columns = "abcdefgh";
    private int[] move=new int[2];
    private boolean whiteMove = true;
    private boolean[][] legalMoves = new boolean[8][8];
    public Move(){
        super();
    }

    public boolean makeMove(String position){
        decodeMove(position);
        if(!computeLegalMoves(whiteMove)){
            System.out.println("No more moves left.");
            return false;
        }
        else if(legalMoves[move[1]][move[0]]){
            this.updatePosition(move[1],move[0],boolToPlayer(whiteMove));
            this.getLocation(move[1],move[0]).setPlayer(boolToPlayer(whiteMove));
            conquerTerritory(boolToPlayer(whiteMove),move[1],move[0]);
            whiteMove=!whiteMove;
            return true;
        }
        System.out.println("This move is illegal! Try again");
        return true;
    }

    //TODO: Implement Game Over correctly
    public boolean makeMove(int[] moveFromGUI){
        this.move=moveFromGUI;
        if(!computeLegalMoves(whiteMove)){
            GameWrapper.board.removeMouse(GameWrapper.board.ma);
            if(getScoreBlack()>getScoreWhite()) {
                GameWrapper.setBlackScoreTextText("");
                GameWrapper.setBlackScoreText("BLACKS WON "+GameWrapper.getBlackScoreText()+" : "+GameWrapper.getWhiteScoreText());
            }
            if(getScoreWhite()>getScoreBlack()){
                GameWrapper.setBlackScoreTextText("");
                GameWrapper.setBlackScoreText("WHITES WON "+GameWrapper.getWhiteScoreText()+" : "+GameWrapper.getBlackScoreText());
            }
            else{
                GameWrapper.setBlackScoreTextText("");
                GameWrapper.setBlackScoreText("DRAW");
            }
            GameWrapper.setWhiteScoreTextText("GAME OVER");
            GameWrapper.setWhiteScoreText("");
            return false;
        }
        else if(legalMoves[move[1]][move[0]]){
            this.updatePosition(move[1],move[0],boolToPlayer(whiteMove));
            this.getLocation(move[1],move[0]).setPlayer(boolToPlayer(whiteMove));
            conquerTerritory(boolToPlayer(whiteMove),move[1],move[0]);
            whiteMove=!whiteMove;
            return true;
        }
        return true;
    }


    private void decodeMove(String position){
        for(int i=0;i<this.columns.length();i++){
            if(position.charAt(0)==columns.charAt(i)){
                move[0]=i; // column
                i=this.columns.length();
            }
        }
        move[1] = Character.getNumericValue(position.charAt(1))-1; //row
    }

    private boolean computeLegalMoves(boolean player){
        legalMoves = new boolean[8][8];
        boolean atLeastOnceLegal = false;
        for(int row=0;row<getBoard().length;row++){
            for(int column=0;column<getBoard()[row].length;column++){
                if(getPieceAtLocation(row,column)==0){
                    int moves=0;
                    if(checkMoveValidity(player,1,-1,row,column)) moves = flipBit(moves,1);
                    if(checkMoveValidity(player,1,0,row,column)) moves = flipBit(moves,2);
                    if(checkMoveValidity(player,1,1,row,column)) moves = flipBit(moves,3);
                    if(checkMoveValidity(player,0,-1,row,column)) moves = flipBit(moves,4);
                    if(checkMoveValidity(player,0,1,row,column)) moves = flipBit(moves,5);
                    if(checkMoveValidity(player,-1,-1,row,column)) moves = flipBit(moves,6);
                    if(checkMoveValidity(player,-1,0,row,column)) moves = flipBit(moves,7);
                    if(checkMoveValidity(player,-1,1,row,column)) moves = flipBit(moves,8);
                    if(moves>0) {
                        legalMoves[row][column]=true;
                        atLeastOnceLegal = true;
                    }
                }
            }
        }
        return atLeastOnceLegal;
    }

    private boolean checkMoveValidity(boolean player,int dRow, int dColumn, int row, int column){
        boolean otherPlayer = !player;
        if(row+dRow<0 || row+dRow>7)
            return false;
        if(row+2*dRow<0 || row+2*dRow>7)
            return false;
        if(column+dColumn<0 || column+dColumn>7)
            return false;
        if(column+2*dColumn<0 || column+2*dColumn>7)
            return false;
        if(this.getPieceAtLocation(row+dRow,column+dColumn)!=boolToPlayer(otherPlayer))
            return false;
        return checkMatch(boolToPlayer(player),dRow,dColumn,row,column);
    }

    private boolean checkMatch(int player,int dRow, int dColumn, int row, int column){
        if(getPieceAtLocation(row,column)==player)
            return true;
        if(row+dRow<0 || row+dRow>7)
            return false;
        if(column+dColumn<0 || column+dColumn>7)
            return false;
        if(this.getPieceAtLocation(row+dRow,column+dColumn)==0)
            return false;
        return checkMatch(player,dRow,dColumn,row+dRow,column+dColumn);
    }

    private int flipBit(int number, int position) {
        return number ^ (1 << position);
    }

    private void conquerTerritory(int conqueror, int row, int column){
        conquerLine(conqueror,1,-1,row,column);
        conquerLine(conqueror,1,-1,row,column);
        conquerLine(conqueror,1,0,row,column);
        conquerLine(conqueror,1,1,row,column);
        conquerLine(conqueror,0,-1,row,column);
        conquerLine(conqueror,0,1,row,column);
        conquerLine(conqueror,-1,-1,row,column);
        conquerLine(conqueror,-1,0,row,column);
        conquerLine(conqueror,-1,1,row,column);

    }

    private boolean conquerLine(int conqueror, int dRow, int dColumn, int row, int column){
        if(row+dRow<0 || row+dRow>7)
            return false;
        if(column+dColumn<0 || column+dColumn>7)
            return false;
        if(this.getPieceAtLocation(row+dRow,column+dColumn)==conqueror)
            return true;
        if(conquerLine(conqueror,dRow,dColumn,row+dRow,column+dColumn)&&getPieceAtLocation(row+dRow,column+dColumn)!=0){
            this.updatePosition(row+dRow,column+dColumn,conqueror);
            return true;
        }
        return false;
    }

    private int boolToPlayer(boolean whitePlayer){
        if(whitePlayer) return 1;
        else return 2;
    }

    public boolean getMoveAtLocation(int row, int column){
        return legalMoves[row][column];
    }
}
