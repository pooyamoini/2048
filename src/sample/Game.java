package sample;

import java.util.Arrays;
import java.util.Random;

class Game {
    private static Game currentGame;
    private Cell[][] board;
    private int score;

    Game(int n) {
        board = new Cell[n][n];
        initBoard();
        this.score = 0;
        currentGame = this;
        new PlayDisplay();
    }

    int getDimensions() {
        return this.board.length;
    }

    private void initBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Cell();
            }
        }
        this.board[0][2].setValue(2);
        this.board[2][1].setValue(4);
    }

    static Game getCurrentGame() {
        return currentGame;
    }

    Cell[][] getBoard() {
        return board;
    }

    static void setCurrentGameNull() {
        for (int i = 0; i < currentGame.board.length; i++) {
            for (int j = 0; j < currentGame.board.length; j++) {
                currentGame.board[i][j].setValue(0);
            }
        }
        currentGame = null;
    }

    void left() {
        boolean doIt = false;
        int[][] previousSituation = new int[this.board.length][this.board[0].length];
        fillCellsArray(previousSituation);
        while (true) {
            if (simpleLeft(this.board) || doIt)
                doIt = true;
            if (shiftLeft(this.board) || doIt)
                doIt = true;
            if (simpleLeft(this.board) || doIt)
                doIt = true;
            if (!shiftLeft(this.board) || doIt)
                break;
            else doIt = true;
        }
        if (doIt || checkInputRandom(previousSituation))
            pickRandom();
        resetCells();
    }

    void right() {
        boolean doIt = false;
        int[][] previousSituation = new int[this.board.length][this.board[0].length];
        fillCellsArray(previousSituation);
        while (true) {
            if (simpleRight(this.board) || doIt)
                doIt = true;
            if (shiftRight(this.board) || doIt)
                doIt = true;
            if (simpleRight(this.board) || doIt)
                doIt = true;
            if (!shiftRight(this.board) || doIt)
                break;
            else doIt = true;
        }
        if (doIt || checkInputRandom(previousSituation))
            pickRandom();
        resetCells();
    }

    void up() {
        boolean doIt = false;
        int[][] previousSituation = new int[this.board.length][this.board[0].length];
        fillCellsArray(previousSituation);
        while (true) {
            if (simpleUp(this.board) || doIt)
                doIt = true;
            if (shiftUp(this.board) || doIt)
                doIt = true;
            if (simpleUp(this.board) || doIt)
                doIt = true;
            if (!shiftUp(this.board) || doIt)
                break;
            else doIt = true;
        }
        if (doIt || checkInputRandom(previousSituation))
            pickRandom();
        resetCells();
    }

    void down() {
        boolean doIt = false;
        int[][] previousSituation = new int[this.board.length][this.board[0].length];
        fillCellsArray(previousSituation);
        while (true) {
            if (simpleDown(this.board) || doIt)
                doIt = true;
            if (shiftDown(this.board) || doIt)
                doIt = true;
            if (simpleDown(this.board) || doIt)
                doIt = true;
            if (!shiftDown(this.board) || doIt)
                break;
            else doIt = true;
        }
        if (doIt || checkInputRandom(previousSituation))
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
                    toReturn = true;
                    cells1[temp + 1].setValue(0);
                    cells1[temp].setValue(value);
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
                    toReturn = true;
                    cells[j - 1].setProductive(true);
                    cells[j - 1].setValue(2 * cells[j].getValue());
                    this.score += (cells[j - 1].getValue());
                    cells[j].setValue(0);
                }

            }
        }
        return toReturn;
    }

    private boolean shiftRight(Cell[][] board) {
        boolean toReturn = false;
        for (Cell[] cells : board) {
            for (int j = 0; j < cells.length; j++) {
                if (j == cells.length - 1 || cells[j].isEmpty())
                    continue;
                int temp = j;
                int value = cells[j].getValue();
                temp++;
                while (true) {
                    if (!cells[temp].isEmpty())
                        break;
                    toReturn = true;
                    cells[temp - 1].setValue(0);
                    cells[temp].setValue(value);
                    if (temp == cells.length - 1)
                        break;
                    temp++;
                }
            }
        }
        return toReturn;
    }

    private boolean simpleRight(Cell[][] board) {
        boolean toReturn = false;
        for (Cell[] cells : board) {
            for (int j = 0; j < cells.length; j++) {
                if (j == cells.length - 1 || cells[j].isEmpty())
                    continue;
                if (cells[j].getValue() == cells[j + 1].getValue()) {
                    if (cells[j + 1].isProductive() || cells[j].isProductive())
                        continue;
                    cells[j + 1].setValue(2 * cells[j + 1].getValue());
                    cells[j + 1].setProductive(true);
                    toReturn = true;
                    this.score += cells[j + 1].getValue();
                    cells[j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean shiftUp(Cell[][] board) {
        boolean toReturn = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || board[i][j].isEmpty())
                    continue;
                int value = board[i][j].getValue();
                int temp = i;
                temp--;
                while (true) {
                    if (!board[temp][j].isEmpty())
                        break;
                    toReturn = true;
                    board[temp + 1][j].setValue(0);
                    board[temp][j].setValue(value);
                    if (temp == 0)
                        break;
                    temp--;
                }
            }
        }
        return toReturn;
    }

    private boolean simpleUp(Cell[][] board) {
        boolean toReturn = false;
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || board[i][j].isEmpty())
                    continue;
                if (board[i][j].getValue() == board[i - 1][j].getValue()) {
                    if (board[i][j].isProductive() || board[i - 1][j].isProductive())
                        continue;
                    toReturn = true;
                    board[i - 1][j].setValue(2 * board[i][j].getValue());
                    board[i - 1][j].setProductive(true);
                    this.score += board[i - 1][j].getValue();
                    board[i][j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean simpleDown(Cell[][] board) {
        boolean toReturn = false;
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((board[i][j]).isEmpty())
                    continue;
                if (board[i][j].getValue() == board[i + 1][j].getValue()) {
                    if (board[i][j].isProductive() || board[i + 1][j].isProductive())
                        continue;
                    toReturn = true;
                    board[i + 1][j].setValue(2 * board[i][j].getValue());
                    board[i + 1][j].setProductive(true);
                    this.score += board[i + 1][j].getValue();
                    board[i][j].setValue(0);
                }
            }
        }
        return toReturn;
    }

    private boolean shiftDown(Cell[][] board) {
        boolean toReturn = false;
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty())
                    continue;
                int value = board[i][j].getValue();
                int temp = i + 1;
                while (true) {
                    if (!board[temp][j].isEmpty())
                        break;
                    toReturn = true;
                    board[temp][j].setValue(value);
                    board[temp - 1][j].setValue(0);
                    if (temp == board[temp].length - 1)
                        break;
                    temp++;
                }
            }
        }
        return toReturn;
    }


    private void pickRandom() {
        int x;
        int y;
        do {
            x = Math.abs(new Random().nextInt() % this.getDimensions());
            y = Math.abs(new Random().nextInt() % this.getDimensions());
        } while (!this.board[x][y].isEmpty());
        switch (x * y % 2) {
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
        Cell[][] cells = Arrays.copyOf(this.board, this.board.length);
        return counter == getDimensions() * getDimensions() && !(simpleLeft(cells) || shiftLeft(cells) || shiftRight(cells) || simpleRight(cells) || shiftDown(cells) || simpleDown(cells) || shiftUp(cells) || simpleUp(cells));
    }

    private void resetCells() {
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                cell.setProductive(false);
            }
        }
    }

    private void fillCellsArray(int[][] cells) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                cells[i][j] = this.board[i][j].getValue();
            }
        }
    }

    private boolean checkInputRandom(int[][] cells) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (cells[i][j] != this.board[i][j].getValue())
                    return true;
            }
        }
        return false;
    }
}
