package sample;

class Cell {

    Cell() {
        this.value = 0;
    }

    private int value;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    boolean isEmpty(){
        return this.value == 0;
    }
}
