package ru.vsu.cs.vinyukov.task2;

public class DominoTile implements DominoSlice{
    @Override
    public int getLeftVal() {
        return 0;
    }

    @Override
    public int getRightVal() {
        return 0;
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}