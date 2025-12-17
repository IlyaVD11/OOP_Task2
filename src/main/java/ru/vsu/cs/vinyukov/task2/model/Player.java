package ru.vsu.cs.vinyukov.task2.model;

import java.util.List;

public interface Player {
    boolean hasNextMove(GameTable table);
    DominoSlice chooseNextMove(GameTable table);
}