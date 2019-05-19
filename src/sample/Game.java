package sample;

import java.util.Arrays;
import java.util.Random;

class Game {
    private static Game currentGame;
    private Cell[][] board;
    private int score;

    Game() {
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
        boolean doIt = false;
        while (true) {
            if (simpleLeft(this.board))
                doIt = true;
            if (shiftLeft(this.board))
                doIt = true;
            if (simpleLeft(this.board))
                doIt = true;
            if (!shiftLeft(this.board))
                break;
        }
        if (doIt)
            pickRandom();
        resetCells();
    }

    void right() {
        boolean doIt = false;
        while (true) {
            if (simpleRight())
                doIt = true;
            if (shiftRight())
                doIt = true;
            if (simpleRight())
                doIt = true;
            if (!shiftRight())
                break;
        }
        if (doIt)
            pickRandom();
        resetCells();
    }

    void up() {
        boolean doIt = false;
        while (true) {
            if (simpleUp())
                doIt = true;
            if (shiftUp())
                doIt = true;
            if (simpleUp())
                doIt = true;
            if (!shiftUp())
                break;
        }
        if (doIt)
            pickRandom();
        resetCells();
    }

    void down() {
        boolean doIt = false;
        while (true) {
            if (simpleDown())
                doIt = true;
            if (shiftDown())
                doIt = true;
            if (simpleDown())
                doIt = true;
            if (!shiftDown())
                break;
        }
        if (doIt)
            pickRandom();
        resetCells();
    }

    private boolean shiftLeft(Cell[][] board) {
        boolean toReturn = false;
        for (Cell[] cells1 : board) {
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
                    toReturn = true;
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        return toReturn;
    }

    private boolean simpleLeft(Cell[][] board) {
        boolean toReturn = false;
        for (Cell[] cells : board) {
            for (int j = cells.length - 1; j >= 0; j--) {
                if (j == 0 || cells[j].isEmpty())
                    continue;
                if (cells[j].getValue() == cells[j - 1].getValue()) {
                    if (cells[j - 1].isProductive() || cells[j].isProductive())
                        continue;
                    cells[j - 1].setProductive(true);
                    cells[j - 1].setValue(2 * cells[j].getValue());
                    toReturn = true;
                    this.score += (2 * cells[j].getValue());
                    cells[j].setValue(0);
                }

            }
        }
        return toReturn;
    }

    private boolean shiftRight() {
        boolean toReturn = false;
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
                    toReturn = true;
                    if (temp == 3)
                        break;
                    temp++;
                }
            }
        }
        return toReturn;
    }

    private boolean simpleRight() {
        boolean toReturn = false;
        for (Cell[] cells : this.board) {
            for (int j = 0; j < cells.length; j++) {
                if (j == 3 || cells[j].isEmpty())
                    continue;
                if (cells[j].getValue() == cells[j + 1].getValue()) {
                    if (cells[j + 1].isProductive() || cells[j].isProductive())
                        continue;
                    cells[j + 1].setValue(2 * cells[j + 1].getValue());
                    cells[j + 1].setProductive(true);
                    this.score += 2 * cells[j].getValue();
                    toReturn = true;
                    cells[j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean shiftUp() {
        boolean toReturn = false;
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
                    toReturn = true;
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        return toReturn;
    }

    private boolean simpleUp() {
        boolean toReturn = false;
        for (int i = this.board.length - 1; i >= 0; i--) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (i == 0 || this.board[i][j].isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i - 1][j].getValue()) {
                    if (this.board[i][j].isProductive() || this.board[i - 1][j].isProductive())
                        continue;
                    this.board[i - 1][j].setValue(2 * this.board[i][j].getValue());
                    this.board[i - 1][j].setProductive(true);
                    toReturn = true;
                    this.score += 2 * this.board[i][j].getValue();
                    this.board[i][j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean simpleDown() {
        boolean toReturn = false;
        for (int i = 0; i < this.board.length - 1; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if ((this.board[i][j]).isEmpty())
                    continue;
                if (this.board[i][j].getValue() == this.board[i + 1][j].getValue()) {
                    if (this.board[i][j].isProductive() || this.board[i + 1][j].isProductive())
                        continue;
                    this.board[i + 1][j].setValue(2 * this.board[i][j].getValue());
                    this.board[i + 1][j].setProductive(true);
                    toReturn = true;
                    this.score += this.board[i][j].getValue();
                    this.board[i][j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean shiftDown() {
        boolean toReturn = false;
        for (int i = 0; i < this.board.length - 1; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].isEmpty())
                    continue;
                int value = this.board[i][j].getValue();
                int temp = i + 1;
                while (true) {
                    if (!this.board[temp][j].isEmpty())
                        break;
                    this.board[temp][j].setValue(value);
                    this.board[temp - 1][j].setValue(0);
                    toReturn = true;
                    if (temp == 3)
                        break;
                    temp++;
                }
            }
        }
        return toReturn;
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

    boolean gameIsOver() {
        int counter = 0;
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                if (!cell.isEmpty())
                    counter++;
            }
        }
        return counter >= 16;
    }

    private void resetCells() {
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                cell.setProductive(false);
            }
        }
    }
}
