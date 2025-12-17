package ru.vsu.cs.vinyukov.task2.model;

import ru.vsu.cs.vinyukov.task2.service.GameManager;

import java.util.List;

public class DominoPlayer implements Player {
    private GameManager gameManager;

    public void setGameManager(GameManager manager) {
        this.gameManager = manager;
    }

    @Override
    public boolean hasNextMove(GameTable table) {
        List<DominoSlice> tiles = gameManager.getPlayerTiles(this);
        for (DominoSlice tile : tiles) {
            if (table.canPlaceTile(tile)) {
                return true;
            }
            DominoSlice flippedTile = tile.flip();
            if (table.canPlaceTile(flippedTile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DominoSlice chooseNextMove(GameTable table) {
        List<DominoSlice> tiles = gameManager.getPlayerTiles(this);
        for (DominoSlice tile : tiles) {
            if (table.canPlaceTile(tile)) {
                return tile;
            }
            DominoSlice flippedTile = tile.flip();
            if (table.canPlaceTile(flippedTile)) {
                return flippedTile;
            }
        }
        return null;
    }
}