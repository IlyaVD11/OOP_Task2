package ru.vsu.cs.vinyukov.task2;

import java.util.List;

public interface GameTable {
    void placeTile(DominoSlice tile);
    List<DominoSlice> getTableTiles();
    boolean canPlaceTile(DominoSlice tile);
}