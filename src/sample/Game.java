package sample;

import java.util.Random;

public class Game {
    private static Game currentGame;
    private Cell[][] board;

    public Game() {
        board = new Cell[4][4];
        initBoard();
        initializeBoard();
        currentGame = this;
    }

    private void initBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    private void initializeBoard() {
        int randX1 = Math.abs(new Random().nextInt(4));
        int randY1 = Math.abs(new Random().nextInt(4));
        int randX2 = Math.abs(new Random().nextInt(4));
        int randY2 = Math.abs(new Random().nextInt(4));
        this.board[randX1][randY1].setValue(2);
        while (!this.board[randX2][randY2].isEmpty()) {
            randX2 = Math.abs(new Random().nextInt(4));
            randY2 = Math.abs(new Random().nextInt(4));
        }
        this.board[randX2][randY2].setValue(4);
    }

    static Game getCurrentGame() {
        return currentGame;
    }

    Cell[][] getBoard() {
        return board;
    }

    static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }

    static void setCurrentGameNull() {
        currentGame = null;
    }

    void left() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == 0 || board[i][j].isEmpty())
                    continue;
                int temp = j;
                int value = board[i][j].getValue();
                j--;
                while (j >= 0 || board[i][j].isEmpty()) {
                    board[i][j + 1].setValue(0);
                    board[i][j].setValue(value);
                    j--;
                }
                j = temp;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                if (j == 0 || board[i][j].isEmpty())
                    continue;
                if (board[i][j].getValue() == board[i][j - 1].getValue()) {
                    board[i][j].setValue(0);
                    board[i][j - 1].setValue(2 * board[i][j].getValue());
                }
            }
        }
    }

    void right() {

    }

    void up() {

    }

    void down() {

    }
}
