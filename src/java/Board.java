public abstract class Board {
    private String columns = "abcdefgh";
    private BoardLocation board[][];
    public Board(){
        this.board=new BoardLocation[8][8]; // rows x columns
        initializeBoard();
    }

    private void initializeBoard(){
        for(int row=0;row<this.board.length;row++){
            for(int col=0;col<this.board[row].length;col++){
                this.board[row][col]=new BoardLocation(row,col);
            }
        }
        this.board[this.board.length/2-1][this.board.length/2-1].setPlayer(1);
        this.board[this.board.length/2][this.board.length/2].setPlayer(1);
        this.board[this.board.length/2][this.board.length/2-1].setPlayer(2);
        this.board[this.board.length/2-1][this.board.length/2].setPlayer(2);
    }
    public BoardLocation[][] getBoard() {
        return this.board;
    }
    public void displayBoard(){
        System.out.println(columns);
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board[i].length;j++){
                System.out.print(this.board[i][j].getPlayer());
            }
            System.out.println(" | "+(i+1));
        }
    }
    public void updatePosition(int row, int column, int player){
        this.board[row][column].setPlayer(player);
    }

    public int getPieceAtLocation(int row, int column){
        return this.board[row][column].getPlayer();
    }

    public BoardLocation getLocation(int row, int column){
        return this.board[row][column];
    }

    public abstract boolean makeMove(String position);
}
