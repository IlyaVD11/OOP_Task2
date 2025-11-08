package ru.vsu.cs.vinyukov.task2;

import java.util.ArrayList;
import java.util.List;

public class DominoPlayer implements Player{
    private String name;
    private List<DominoSlice> tiles;

    public DominoPlayer(String name) {
        this.name = name;
        this.tiles = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<DominoSlice> getTiles() {
        return tiles;
    }

    @Override
    public void addTile(DominoSlice tile) {
        tiles.add(tile);
    }

    @Override
    public boolean hasNextMove(GameTable table) {
        for (DominoSlice tile : tiles) {
            if (table.canPlaceTile(tile)) {
                return true;
            }
            DominoSlice flippedTile = new DominoTile(tile.getRightVal(), tile.getLeftVal());
            if (table.canPlaceTile(flippedTile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DominoSlice chooseNextMove(GameTable table) {
        for (DominoSlice tile : tiles) {
            if (table.canPlaceTile(tile)) {
                return tile;
            }
            DominoSlice flippedTile = new DominoTile(tile.getRightVal(), tile.getLeftVal());
            if (table.canPlaceTile(flippedTile)) {
                return flippedTile;
            }
        }
        return null;
    }
}