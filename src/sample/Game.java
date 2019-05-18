package sample;

import java.util.Random;

public class Game {
    private static Game currentGame;
    private Cell[][] board;
    private int score;

    public Game() {
        board = new Cell[4][4];
        initBoard();
        initializeBoard();
        this.score = 0;
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

    static void setCurrentGameNull() {
        currentGame = null;
    }

    void left() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (j == 0 || this.board[i][j].isEmpty())
                    continue;
                int temp = j;
                int value = this.board[i][j].getValue();
                temp--;
                while (true) {
                    if (!this.board[i][temp].isEmpty())
                        break;
                    this.board[i][temp + 1].setValue(0);
                    this.board[i][temp].setValue(value);
                    PlayDisplay.getLabels()[i][j].setText(Integer.toString(this.board[i][j].getValue()));
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        for (int i = 0; i < this.board.length; i++) {
            for (int j = this.board[i].length - 1; j >= 0; j--) {
                if (j == 0 || this.board[i][j].isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i][j - 1].getValue()) {
                    this.board[i][j - 1].setValue(2 * this.board[i][j].getValue());
                    PlayDisplay.getLabels()[i][j].setText(Integer.toString(2 * this.board[i][j].getValue()));
                    this.score += (2 * this.board[i][j].getValue());
                    this.board[i][j].setValue(0);
                }

            }
        }
        pickRandom();
    }

    void right() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (j == 3 || this.board[i][j].isEmpty())
                    continue;
                int temp = j;
                int value = this.board[i][j].getValue();
                temp ++;
                while (true){
                    if (!this.board[i][temp].isEmpty())
                        break;
                    this.board[i][temp - 1].setValue(0);
                    this.board[i][temp].setValue(value);
                    if (temp == 3)
                        break;
                    temp++;
                }
            }
        }
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (j == 3 || this.board[i][j].isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i][j + 1].getValue()){
                    this.board[i][j + 1].setValue(2 * this.board[i][j + 1].getValue());
                    this.score += 2 * this.board[i][j].getValue();
                    this.board[i][j].setValue(0);
                }
            }
        }
        pickRandom();
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

    int getScore() {
        return score;
    }
}
