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
        Cell[][] cells = new Cell[this.board.length][this.board[0].length];
        fillCellsArray(cells);
        while (true) {
            simpleLeft(this.board);
            shiftLeft(this.board);
            simpleLeft(this.board);
            if (!shiftLeft(this.board))
                break;
        }
        if (!checkEqualArray(cells))
            pickRandom();
        resetCells();
    }

    void right() {
        Cell[][] cells = new Cell[this.board.length][this.board[0].length];
        fillCellsArray(cells);
        while (true) {
            simpleRight(this.board);
            shiftRight(this.board);
            simpleRight(this.board);
            if (!shiftRight(this.board))
                break;
        }
        if (!checkEqualArray(cells))
            pickRandom();
        resetCells();
    }

    void up() {
        Cell[][] cells = new Cell[this.board.length][this.board[0].length];
        fillCellsArray(cells);
        while (true) {
            simpleUp(this.board);
            shiftUp(this.board);
            simpleUp(this.board);
            if (!shiftUp(this.board))
                break;
        }
        if (!checkEqualArray(cells))
            pickRandom();
        resetCells();
    }

    void down() {
        Cell[][] cells = new Cell[this.board.length][this.board[0].length];
        fillCellsArray(cells);
        while (true) {
            simpleDown(this.board);
            shiftDown(this.board);
            simpleDown(this.board);
            if (!shiftDown(this.board))
                break;
        }
        if (!checkEqualArray(cells))
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
                    cells[temp - 1].setValue(0);
                    cells[temp].setValue(value);
                    toReturn = true;
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
                    this.score += cells[j + 1].getValue();
                    toReturn = true;
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
                    board[temp + 1][j].setValue(0);
                    board[temp][j].setValue(value);
                    toReturn = true;
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
                    board[i - 1][j].setValue(2 * board[i][j].getValue());
                    board[i - 1][j].setProductive(true);
                    toReturn = true;
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
                    board[i + 1][j].setValue(2 * board[i][j].getValue());
                    board[i + 1][j].setProductive(true);
                    toReturn = true;
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
                    board[temp][j].setValue(value);
                    board[temp - 1][j].setValue(0);
                    toReturn = true;
                    if (temp == board[temp].length - 1)
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
        Cell[][] cells = Arrays.copyOf(this.board, this.board.length);
        return counter == 16 && !(simpleLeft(cells) || shiftLeft(cells) || shiftRight(cells) || simpleRight(cells) || shiftDown(cells) || simpleDown(cells) || shiftUp(cells) || simpleUp(cells));
    }

    private void resetCells() {
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                cell.setProductive(false);
            }
        }
    }

    private void fillCellsArray(Cell[][] cells) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setValue(this.board[i][j].getValue());
            }
        }
    }

    private boolean checkEqualArray(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getValue() != this.board[i][j].getValue())
                    return false;
            }
        }
        return true;
    }
}
