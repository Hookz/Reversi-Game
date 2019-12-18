public class BoardLocation {
    private int horizontalLocation;
    private int verticalLocation;
    private int player;
    private GamePiece piece;

    public BoardLocation(){
        this.horizontalLocation=0;
        this.verticalLocation=0;
        this.player=0;
        this.piece=null;
    }
    public BoardLocation(int x, int y, int player){
        this.horizontalLocation=x;
        this.verticalLocation=y;
        this.player=player;
        this.piece=null;
    }
    public BoardLocation(int x, int y){
        this.horizontalLocation=x;
        this.verticalLocation=y;
        this.player=0;
        this.piece=null;
    }

    public int getHorizontalLocation() {
        return horizontalLocation;
    }

    public int getPlayer() {
        return player;
    }

    public int getVerticalLocation() {
        return verticalLocation;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
