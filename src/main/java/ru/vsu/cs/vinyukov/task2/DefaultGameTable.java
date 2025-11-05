package ru.vsu.cs.vinyukov.task2;

import java.util.List;

public class DefaultGameTable implements GameTable{
    @Override
    public void placeTile(DominoSlice tile) {

    }

    @Override
    public List<DominoSlice> getTableTiles() {
        return List.of();
    }

    @Override
    public boolean canPlaceTile() {
        return false;
    }
}
