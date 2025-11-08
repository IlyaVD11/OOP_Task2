package ru.vsu.cs.vinyukov.task2;

public class DominoTile implements DominoSlice{
    private final int leftVal;
    private final int rightVal;

    public DominoTile(int leftVal, int rightVal) {
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }

    @Override
    public int getLeftVal() {
        return leftVal;
    }

    @Override
    public int getRightVal() {
        return rightVal;
    }

    @Override
    public boolean isDouble() {
        return leftVal == rightVal;
    }

    @Override
    public String toString() {
        return "[" + leftVal + "|" + rightVal + "]";
    }

    public DominoSlice flip() {
        return new DominoTile(rightVal, leftVal);
    }
}