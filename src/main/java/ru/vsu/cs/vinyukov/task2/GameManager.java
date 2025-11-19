package ru.vsu.cs.vinyukov.task2;

public interface GameManager {
    void startGame();
    boolean nextTurn();
    GameTable getGameTable();
    boolean isGameOver();
}