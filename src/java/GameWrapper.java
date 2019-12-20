import javax.swing.*;
import java.awt.*;

public class GameWrapper extends JPanel {
    public static VisualBoard board;
    public static JFrame window;
    private static JPanel sidePanel;
    public GameWrapper(){
        float fontSize = 30.0f;
        board = new VisualBoard();
        window = new JFrame("Reversi");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.add(board,c);
        c.ipadx = 100;
        c.insets = new Insets(0,60,0,60);
        sidePanel = new JPanel(new GridLayout(5,1));
        JLabel whiteScore = new JLabel(num(2),JLabel.CENTER);
        whiteScore.setFont(whiteScore.getFont().deriveFont(fontSize));
        JLabel blackScore = new JLabel(num(2),JLabel.CENTER);
        blackScore.setFont(blackScore.getFont().deriveFont(fontSize));
        JLabel whiteScoreText = new JLabel("White score",JLabel.CENTER);
        whiteScoreText.setFont(whiteScoreText.getFont().deriveFont(fontSize));
        JLabel blackScoreText = new JLabel("Black score",JLabel.CENTER);
        blackScoreText.setFont(blackScoreText.getFont().deriveFont(fontSize));
        whiteScoreText.setVisible(true);
        JButton hints = new JButton("<html>HINTS: <strong style='color:RED;'>OFF</strong></html>");
        hints.setMargin(new Insets(20,0,20,0));
        hints.setFont(hints.getFont().deriveFont(fontSize/2));
        hints.addActionListener(e -> {
            board.setHints(!board.getHints());
            if(board.getHints()){
                hints.setText("<html>HINTS: <strong style='color:GREEN;'>ON</strong></html>");
            }
            else{
                hints.setText("<html>HINTS: <strong style='color:RED;'>OFF</strong></html>");
            }
            board.repaint();
        });
        sidePanel.add(whiteScoreText);
        sidePanel.add(whiteScore);
        sidePanel.add(blackScoreText);
        sidePanel.add(blackScore);
        sidePanel.add(hints);

        window.add(sidePanel,c);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }

    private static String num(int number){
        return Integer.toString(number);
    }

    public static void setWhiteScoreText(String whiteScore) {
        JLabel wS = (JLabel) sidePanel.getComponent(1);
        wS.setText(whiteScore);
    }

    public static String getWhiteScoreText() {
        JLabel wS = (JLabel) sidePanel.getComponent(1);
        return wS.getText();
    }

    public static void setBlackScoreText(String blackScore) {
        JLabel bS = (JLabel) sidePanel.getComponent(3);
        bS.setText(blackScore);
    }

    public static String getBlackScoreText() {
        JLabel bS = (JLabel) sidePanel.getComponent(3);
        return bS.getText();
    }

    public static void setWhiteScoreTextText(String whiteScoreText){
        JLabel wST = (JLabel) sidePanel.getComponent(0);
        wST.setText(whiteScoreText);
    }
    public static void setBlackScoreTextText(String blackScoreText){
        JLabel bST = (JLabel) sidePanel.getComponent(2);
        bST.setText(blackScoreText);
    }
    public static void gameOver(){
        board.gameOver();
    }
}
