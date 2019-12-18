import javax.swing.*;
import java.awt.*;

public class GameWrapper extends JPanel {
    public static VisualBoard board;
    public static JFrame window;
    private static JLabel whiteScore;
    private static JLabel blackScore;
    private static JLabel whiteScoreText;
    private static JLabel blackScoreText;
    public GameWrapper(){
        float fontSize = 30.0f;
        JPanel sidePanel = new JPanel(new GridLayout(4,1));
        whiteScore = new JLabel(num(2),JLabel.CENTER);
        whiteScore.setFont(whiteScore.getFont().deriveFont(fontSize));
        blackScore = new JLabel(num(2),JLabel.CENTER);
        blackScore.setFont(blackScore.getFont().deriveFont(fontSize));
        whiteScoreText = new JLabel("White score",JLabel.CENTER);
        whiteScoreText.setFont(whiteScoreText.getFont().deriveFont(fontSize));
        blackScoreText = new JLabel("Black score",JLabel.CENTER);
        blackScoreText.setFont(blackScoreText.getFont().deriveFont(fontSize));
        sidePanel.add(whiteScoreText);
        sidePanel.add(whiteScore);
        sidePanel.add(blackScoreText);
        sidePanel.add(blackScore);
        board = new VisualBoard();
        window = new JFrame("Reversi");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.add(board,c);
        c.ipadx = 100;
        window.add(sidePanel,c);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }

    private static String num(int number){
        return Integer.toString(number);
    }

    public static void setWhiteScoreText(String whiteScore) {
        GameWrapper.whiteScore.setText(whiteScore);
    }

    public static void setBlackScoreText(String blackScore) {
        GameWrapper.blackScore.setText(blackScore);
    }
    public static void setWhiteScoreTextText(String whiteScoreText){
        GameWrapper.whiteScoreText.setText(whiteScoreText);
    }
    public static void setBlackScoreTextText(String blackScoreText){
        GameWrapper.blackScoreText.setText(blackScoreText);

    }
}
