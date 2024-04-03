import java.lang.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class TicTacToe {
    // global variables
    public static char [][] gameBoard = {
            {' ', '|', ' ', '|', ' ' },
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' ' },
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' ' }};
    public static ArrayList<Integer> usedPositions = new ArrayList<>();
    public static void printGameBoard() {
        for (char[] row: gameBoard) {
            for(char c: row) {
                System.out.print(c);
            }
            System.out.println(" ");
        }
    }

    public static void placePiece(int pos, String user) {
        char symbol;
        if ("player".equals(user)) {
            symbol = 'X';
        }
        else {
            symbol = 'O';
        }

        // FIXME: check how to do this
        usedPositions.add(pos);

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
        printGameBoard();

        // TODO: method checking if player has won
        if (checkWinner(pos)) {
            System.out.println(user.substring(0, 1).toUpperCase() + user.substring(1) + " has WON!");
            System.out.println("CONGRATULATION!");
            System.exit(0);
        }
        else {
            if (overGame()) {
                System.exit(0);
            } else {
                if ("player".equals(user)) {
                    computerStep();
                } else {
                    playerStep();
                }
            }
        }
    }

    private static boolean checkWinner(int pos) {
        if (pos == 1) {
            char symbol = gameBoard[0][0];
            // check horizontal
            if (gameBoard[0][2] == symbol && gameBoard[0][4] == symbol) {
                return true;
            }
            // check vertical
            if (gameBoard[2][0] == symbol && gameBoard[4][0] == symbol) {
                return true;
            }
            // check diagonal
            return gameBoard[2][2] == symbol && gameBoard[4][4] == symbol;
        }
        if (pos == 2) {
            char symbol = gameBoard[0][2];
            // check horizontal
            if (gameBoard[0][0] == symbol && gameBoard[0][4] == symbol) {
                return true;
            }
            // check vertical
            return gameBoard[2][2] == symbol && gameBoard[4][2] == symbol;
        }
        if (pos == 3) {
            char symbol = gameBoard[0][4];
            // check horizontal
            if (gameBoard[0][2] == symbol && gameBoard[0][0] == symbol) {
                return true;
            }
            // check vertical
            if (gameBoard[2][4] == symbol && gameBoard[4][4] == symbol) {
                return true;
            }
            // check diagonal
            return gameBoard[2][2] == symbol && gameBoard[4][0] == symbol;
        }
        if (pos == 4) {
            char symbol = gameBoard[2][0];
            // check horizontal
            if (gameBoard[2][2] == symbol && gameBoard[2][4] == symbol) {
                return true;
            }
            // check vertical
            return gameBoard[0][0] == symbol && gameBoard[4][0] == symbol;
        }
        if (pos == 5) {
            char symbol = gameBoard[2][2];
            // check horizontal
            if (gameBoard[2][0] == symbol && gameBoard[2][4] == symbol) {
                return true;
            }
            // check vertical
            if (gameBoard[0][2] == symbol && gameBoard[4][2] == symbol) {
                return true;
            }
            // check diagonal
            return (gameBoard[0][0] == symbol && gameBoard[4][4] == symbol) || (gameBoard[0][4] == symbol && gameBoard[4][0] == symbol);
        }
        if (pos == 6) {
            char symbol = gameBoard[2][4];
            // check horizontal
            if (gameBoard[2][0] == symbol && gameBoard[2][2] == symbol) {
                return true;
            }
            // check vertical
            return gameBoard[0][4] == symbol && gameBoard[4][4] == symbol;
        }
        if (pos == 7) {
            char symbol = gameBoard[4][0];
            // check horizontal
            if (gameBoard[4][2] == symbol && gameBoard[4][4] == symbol) {
                return true;
            }
            // check vertical
            if (gameBoard[0][0] == symbol && gameBoard[2][0] == symbol) {
                return true;
            }
            // check diagonal
            return gameBoard[2][2] == symbol && gameBoard[0][4] == symbol;
        }
        if (pos == 8) {
            char symbol = gameBoard[4][2];
            // check horizontal
            if (gameBoard[4][0] == symbol && gameBoard[4][4] == symbol) {
                return true;
            }
            // check vertical
            return (gameBoard[0][2] == symbol && gameBoard[2][2] == symbol);
        }
        if (pos == 9) {
            char symbol = gameBoard[4][4];
            // check horizontal
            if (gameBoard[4][0] == symbol && gameBoard[4][2] == symbol) {
                return true;
            }
            // check vertical
            if (gameBoard[2][4] == symbol && gameBoard[0][4] == symbol) {
                return true;
            }
            // check diagonal
            return gameBoard[2][2] == symbol && gameBoard[0][4] == symbol;
        }
        return false;
    }

    // FIXME: game doesn't end
    private static boolean overGame() {
        // check free positions available
        if (usedPositions.size() == 8) {
            System.out.println("Game is Over.");
            System.out.println("No winner sellected");
            return true;
        }
        return false;
    }

    private static void playerStep() {
        int pos = getPlayerStep();
        checkPositionNotUsed(pos, "player");
        placePiece(pos, "player");
    }

    private static int getPlayerStep() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your placement (1-9): ");
        return scan.nextInt();
    }

    private static void computerStep() {
        // computer generate its step number
        Random rand = new Random();
        int rand_pos = rand.nextInt(9);
        if (rand_pos == 0) {
            computerStep();
        }
        // check position was not previously taken
        checkPositionNotUsed(rand_pos, "ai");

        // display info about computer move
        System.out.println(" ");
        System.out.println("Computer's next move is -> " + rand_pos);
        System.out.println(" ");
        // making a next step
        placePiece(rand_pos, "ai");
    }

    private static void checkPositionNotUsed(int pos, String user) {
        if (usedPositions.contains(pos)) {
            System.out.println("Position taken.");
            if (user.equals("player")) {
                playerStep();
            }
            else {
                computerStep();
            }
        }
    }

    public static void main(String[] args) {
        int pos = getPlayerStep();
        placePiece(pos, "player");
    }
}
