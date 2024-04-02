import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row: gameBoard) {
            for(char c: row) {
                System.out.print(c);
            }
            System.out.println(" ");
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user, int[] usedPositions) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
        }
        else {
            symbol = 'O';
        }

        // FIXME: check how to do this
        // usedPositions.add(pos);

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
        printGameBoard(gameBoard);
        if (user.equals("player")) {
            computerSteps(gameBoard, usedPositions);
        }
        else {
            playerStep(gameBoard, usedPositions);
        }
    }

    private static void playerStep(char[][] gameBoard, int[] usedPositions) {
        int pos = getPlayerStep();

        placePiece(gameBoard, pos, "player", usedPositions);
    }

    private static int getPlayerStep() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your placement (1-9): ");
        return scan.nextInt();
    }

    private static void computerSteps(char[][] gameBoard, int[] usedPositions) {
        // computer generate its step number
        Random rand = new Random();
        int rand_pos = rand.nextInt(9);

        // TODO: make a working method
        // checkPositionNotUsed(rand_pos, "ai", usedPositions);

        // display info about computer move
        System.out.println(" ");
        System.out.println("Computer's next move is -> " + rand_pos);
        System.out.println(" ");

        placePiece(gameBoard, rand_pos, "ai", usedPositions);
    }

    public static void main(String[] args) {
        char [][] gameBoard = {
                {' ', '|', ' ', '|', ' ' },
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' ' },
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' ' }};
        String user = "player";
        int[] usedPositions = {};

        int pos = getPlayerStep();

        placePiece(gameBoard, pos, "player", usedPositions);
    }
}
