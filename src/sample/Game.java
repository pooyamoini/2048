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
        this.board[1][3].setValue(2);
        this.board[2][2].setValue(4);
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
                temp--;
                while (board[i][temp].isEmpty()) {
                    board[i][temp + 1].setValue(0);
                    board[i][temp].setValue(value);
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        pickRandom();
    }

    void right() {

    }

    void up() {

    }

    void down() {

    }

    private void pickRandom() {
        int x = Math.abs(new Random().nextInt(4));
        int y = Math.abs(new Random().nextInt(4));
        while (!this.board[x][y].isEmpty()) {
            x = Math.abs(new Random().nextInt(4));
            y = Math.abs(new Random().nextInt(4));
        }
        switch (new Random().nextInt() % 2) {
            case 0:
                this.board[x][y].setValue(2);
                return;
            case 1:
                this.board[x][y].setValue(4);
        }
    }
}
