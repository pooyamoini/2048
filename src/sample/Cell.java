package sample;

class Cell {

    private int value;
    private boolean isProductive;

    Cell() {
        this.value = 0;
        this.isProductive = false;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    boolean isEmpty(){
        return this.value == 0;
    }

    boolean isProductive() {
        return isProductive;
    }

    void setProductive(boolean productive) {
        isProductive = productive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell){
            return ((Cell) obj).getValue() == this.value;
        }
        return false;
    }
}
