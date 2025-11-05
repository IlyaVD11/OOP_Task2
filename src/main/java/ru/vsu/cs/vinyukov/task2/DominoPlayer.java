package ru.vsu.cs.vinyukov.task2;

import java.util.List;

public class DominoPlayer implements Player{
    @Override
    public String getName() {
        return "";
    }

    @Override
    public List<DominoSlice> getTiles() {
        return List.of();
    }

    @Override
    public void addTile(DominoSlice tile) {

    }

    @Override
    public boolean hasNextMove(GameTable table) {
        return false;
    }

    @Override
    public DominoSlice chooseNextMove(GameTable table) {
        return null;
    }
}
