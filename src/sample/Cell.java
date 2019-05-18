package sample;

public class Cell {

    public Cell() {
        this.value = 0;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty(){
        return this.value == 0;
    }
}
