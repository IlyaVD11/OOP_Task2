package ru.vsu.cs.vinyukov.task2.model;

public interface DominoSlice {
    int getLeftVal();
    int getRightVal();
    boolean isDouble();
    DominoSlice flip();
}