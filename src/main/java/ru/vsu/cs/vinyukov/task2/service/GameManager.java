package ru.vsu.cs.vinyukov.task2.service;

import ru.vsu.cs.vinyukov.task2.model.GameTable;

public interface GameManager {
    void startGame();
    boolean nextTurn();
    GameTable getGameTable();
    boolean isGameOver();
}