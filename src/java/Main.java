import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board gameBoard = new Move();
        gameBoard.displayBoard();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            if(gameBoard.makeMove(in.next()))
                gameBoard.displayBoard();
            else{
                System.out.println("GAME OVER!");
                break;
            }
        }
    }
}
