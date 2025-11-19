package ru.vsu.cs.vinyukov.task2;

import java.util.List;

public interface Player {
    List<DominoSlice> getTiles();
    void addTile(DominoSlice tile);
    boolean hasNextMove(GameTable table);
    DominoSlice chooseNextMove(GameTable table);
}