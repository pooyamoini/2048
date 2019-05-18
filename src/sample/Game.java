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
        for (Cell[] cells1 : this.board) {
            for (int j = 0; j < cells1.length; j++) {
                if (j == 0 || cells1[j].isEmpty())
                    continue;
                int temp = j;
                int value = cells1[j].getValue();
                temp--;
                while (true) {
                    if (!cells1[temp].isEmpty())
                        break;
                    cells1[temp + 1].setValue(0);
                    cells1[temp].setValue(value);
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        for (Cell[] cells : this.board) {
            for (int j = cells.length - 1; j >= 0; j--) {
                if (j == 0 || cells[j].isEmpty())
                    continue;
                if (cells[j].getValue() == cells[j - 1].getValue()) {
                    cells[j - 1].setValue(2 * cells[j].getValue());
                    this.score += (2 * cells[j].getValue());
                    cells[j].setValue(0);
                }

            }
        }
        pickRandom();
    }

    void right() {
        for (Cell[] cells : this.board) {
            for (int j = 0; j < cells.length; j++) {
                if (j == 3 || cells[j].isEmpty())
                    continue;
                int temp = j;
                int value = cells[j].getValue();
                temp++;
                while (true) {
                    if (!cells[temp].isEmpty())
                        break;
                    cells[temp - 1].setValue(0);
                    cells[temp].setValue(value);
                    if (temp == 3)
                        break;
                    temp++;
                }
            }
        }
        for (Cell[] cells : this.board) {
            for (int j = 0; j < cells.length; j++) {
                if (j == 3 || cells[j].isEmpty())
                    continue;
                if (cells[j].getValue() == cells[j + 1].getValue()) {
                    cells[j + 1].setValue(2 * cells[j + 1].getValue());
                    this.score += 2 * cells[j].getValue();
                    cells[j].setValue(0);
                }
            }
        }
        pickRandom();
    }

    void up() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (i == 0 || this.board[i][j].isEmpty())
                    continue;
                int value = this.board[i][j].getValue();
                int temp = i;
                temp--;
                while (true) {
                    if (!this.board[temp][j].isEmpty())
                        break;
                    this.board[temp + 1][j].setValue(0);
                    this.board[temp][j].setValue(value);
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        for (int i = this.board.length - 1; i >= 0; i--) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (i == 0 || this.board[i][j].isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i - 1][j].getValue()) {
                    this.board[i - 1][j].setValue(2 * this.board[i][j].getValue());
                    this.score += 2 * this.board[i][j].getValue();
                    this.board[i][j].setValue(0);
                }
            }
        }
        pickRandom();
    }

    void down() {
        for (int i = 0; i < this.board.length - 1; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].isEmpty())
                    continue;
                int value = this.board[i][j].getValue();
                int temp = i + 1;
                while (true){
                    if (!this.board[temp][j].isEmpty())
                        break;
                    this.board[temp][j].setValue(value);
                    this.board[temp - 1][j].setValue(0);
                    if (temp == 3)
                        break;
                    temp++;
                }
            }
        }
        for (int i = 0; i < this.board.length - 1; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if ((this.board[i][j]).isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i + 1][j].getValue()){
                    this.board[i + 1][j].setValue(2 * this.board[i][j].getValue());
                    this.score += this.board[i][j].getValue();
                    this.board[i][j].setValue(0);
                }
            }
        }
        pickRandom();
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
