import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VisualBoard extends JPanel {
    private boolean hints = false;
    public MouseListener ma;
    private Board gameBoard;
    public JPanel window;
    private int size;
    private Rectangle2D[][] boardFields = new Rectangle2D.Double[8][8];
    public VisualBoard(){
        this.size = 50;
        setPreferredSize(new Dimension(8*size,8*size));
        window = new JPanel(null);
        gameBoard = new Move();
        renderBoard();
        window.setVisible(true);
        ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i=0;i<gameBoard.getBoard().length;i++){
                    for(int j=0;j<gameBoard.getBoard()[i].length;j++){
                        if(boardFields[i][j].contains(e.getX(),e.getY())){
                            if(gameBoard.makeMove(new int[]{j,i})) {
                                gameBoard.calculateScores();
                                GameWrapper.setBlackScoreText(num(gameBoard.getScoreBlack()));
                                GameWrapper.setWhiteScoreText(num(gameBoard.getScoreWhite()));
                                repaint();
                            }
                            else{
                                removeMouse(ma);
                                return;
                            }
                        }
                    }
                }
            }
            public void mousePressed(MouseEvent e) {
                for(int i=0;i<gameBoard.getBoard().length;i++){
                    for(int j=0;j<gameBoard.getBoard()[i].length;j++){
                        if(boardFields[i][j].contains(e.getX(),e.getY())){
                            if(gameBoard.makeMove(new int[]{j,i})) {
                                gameBoard.calculateScores();
                                GameWrapper.setBlackScoreText(num(gameBoard.getScoreBlack()));
                                GameWrapper.setWhiteScoreText(num(gameBoard.getScoreWhite()));
                                repaint();
                            }
                            else{
                                removeMouse(ma);
                                return;
                            }
                        }
                    }
                }
            }
        };
        this.addMouseListener(ma);
    }

    public void removeMouse(MouseListener ml){
        this.removeMouseListener(ml);
    }

    private void renderBoard(){
        for(int i=0;i<gameBoard.getBoard().length;i++) {
            for (int j = 0; j < gameBoard.getBoard()[i].length; j++) {
                window.add(this);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(getVisibleRect());
        g2.setColor(Color.GRAY);
        for(int i=0;i<gameBoard.getBoard().length;i++){
            g2.drawLine(i*size,0,i*size,gameBoard.getBoard().length*size);
            for(int j=0;j<gameBoard.getBoard()[i].length;j++){
                g2.drawLine(0,i*size,gameBoard.getBoard().length*size, i*size);
            }
        }

        g2.setColor(Color.LIGHT_GRAY);
        for(int i=0;i<gameBoard.getBoard().length;i++){
            for(int j=0;j<gameBoard.getBoard()[i].length;j++){
                boardFields[i][j] = new Rectangle2D.Double(i * size + 1, j * size + 1, size - 1, size - 1);
                if(gameBoard.getMoveAtLocation(i,j)&&hints){
                    g2.setColor(new Color(21, 189, 66));
                    g2.fill(boardFields[i][j]);
                    g2.setColor(Color.LIGHT_GRAY);
                }
                else{
                    g2.fill(boardFields[i][j]);
                }
            }
        }
        for(int i=0;i<gameBoard.getBoard().length;i++){
            for(int j=0;j<gameBoard.getBoard()[i].length;j++){
                g2.setColor(getPlayerColor(gameBoard.getPieceAtLocation(i,j)));
                g2.fill(new Ellipse2D.Double(i * size + 1, j * size + 1, size - 1, size - 1));
            }
        }
    }

    public void gameOver(){
        // Does not work with JAR files
        /*
        try
        {
            boolean saveHints = hints;
            hints = false;
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            File file = new File(getClass().getResource("/img/gameStateAtOver.png").getPath());
            Graphics2D graphics2D = image.createGraphics();
            paint(graphics2D);
            ImageIO.write(image,"png", file);
            hints = saveHints;
        } catch(IOException exception)
        {
            System.out.println("Could not generate image");
        }*/
    }
    private Color getPlayerColor (int player){
        if(player==1) return Color.WHITE;
        if(player==2) return Color.BLACK;
        return Color.LIGHT_GRAY;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Rectangle2D[][] getBoardFields() {
        return boardFields;
    }

    private static String num(int number){
        return Integer.toString(number);
    }
    public void setHints(boolean hints){
        this.hints = hints;
    }
    public boolean getHints(){
        return this.hints;
    }
}
