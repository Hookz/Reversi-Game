import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoardLocation{
    private int horizontalLocation;
    private int verticalLocation;
    private int player;

    public BoardLocation(){
        this.horizontalLocation=0;
        this.verticalLocation=0;
        this.player=0;
    }
    public BoardLocation(int x, int y){
        this.horizontalLocation=x;
        this.verticalLocation=y;
        this.player=0;
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

    public void setPlayer(int player) {
        this.player = player;
    }
}
