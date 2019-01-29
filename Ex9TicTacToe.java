package exercises;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

/*
 *  The TicTacToe Game
 *  See https://en.wikipedia.org/wiki/Tic-tac-toe.
 *
 *  This is exercising functional decomposition and testing
 *  - Any non trivial method should be tested (in test() method far below)
 *  - IO methods never tested.
 *
 *  NOTE: Just use an array for the board (we print it to look square, see plotBoard())
 *
 */
public class Ex9TicTacToe {

    public static void main(String[] args) {
        new Ex9TicTacToe().program();
    }

    final Scanner sc = new Scanner(in);
    final Random rand = new Random();
    final char EMPTY = '-';        // This is so that we easy can change the value in one place

    void program() {
        //test();       // <--------- Comment out to test

        Player p1 = new Player("Axel", 'X');
        Player p2 = new Player("Theodor", 'O');
        Player actual;
        Player winner = null;
        char[] board = createBoard();  // TODO

        out.println("Welcome to Tic Tac Toe, board is ...");
        plotBoard(board);

        actual = randPlayer(p1, p2);

        // -- Input ----------
        for (int i = 0; i < 9; i++) {
            out.printf("Player is: %s(%s)%n", actual.name, actual.mark);

            board = getPlay(actual, board);
            plotBoard(board);

            // --- Process ----------

            if (checkWin(board)) {
                winner = actual;
                break;
            }

            actual = actual != p1 ? p1 : p2;
        }

        // -- Output --------


        out.println("Game over!");

        if (winner != null) {
            out.println("Winner is " + winner.name);
        } else {
            out.println("Draw");
        }
    }


    // ---------- Methods below this ----------------

    boolean checkWin(char[] board) {
        return  allEqual(0, 1, 2, board) ||
                allEqual(3, 4, 5, board) ||
                allEqual(6, 7, 8, board) ||
                allEqual(0, 3, 6, board) ||
                allEqual(1, 4, 7, board) ||
                allEqual(2, 5, 8, board) ||
                allEqual(0, 4, 8, board) ||
                allEqual(2, 4, 6, board);
    }

    boolean allEqual(int n1, int n2, int n3, char[] board) {
        return board[n1] == board[n2] && board[n2] == board[n3] && board[n1] != EMPTY;
    }

    char[] getPlay(Player player, char[] board) {
        out.println("Select position to put mark (0-8) > ");
        int play = sc.nextInt();
        sc.nextLine();

        if(!validPlay(play, board)) {
            out.println("That wasn't a valid move");
            return getPlay(player, board);
        }

        board[play] = player.mark;
        return board;
    }

    boolean validPlay(int play, char[] board) {
        return board[play] == EMPTY; // && play <= 8 && play >= 0;
    }

    Player randPlayer(Player p1, Player p2) {
        if(rand.nextInt(2) == 0) {
            return p1;
        } else {
            return p2;
        }
    }

    char[] createBoard() {
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = EMPTY;
        }
        return board;
    }


    void plotBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            out.print(board[i] + " ");
            if ((i + 1) % 3 == 0) {
                out.println();
            }
        }
    }

    // A class (blueprint) for players.
    class Player {
        String name;
        char mark;

        Player(String name, char mark) {  // A constructor to initialize
            this.name = name;  // "this" is the actual object we're initializing
            this.mark = mark;  // Used here because of name clashes parameters/instance variables
        }
    }

    // This is used to test methods in isolation
    // Any non trivial method should be tested.
    // If not ... can't build a solution out of possible failing parts!
    void test() {
        char[] b = createBoard();
        out.println(b.length == 9);  // Should be true


        exit(0);
    }
}