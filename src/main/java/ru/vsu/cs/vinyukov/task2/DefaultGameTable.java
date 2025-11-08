package ru.vsu.cs.vinyukov.task2;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameTable implements GameTable{
    private List<DominoSlice> tableTiles;

    public DefaultGameTable() {
        tableTiles = new ArrayList<>();
    }

    @Override
    public void placeTile(DominoSlice tile) {
        if (tableTiles.isEmpty()) {
            tableTiles.add(tile);
            return;
        }

        DominoSlice lastTile = tableTiles.get(tableTiles.size() - 1);
        if (lastTile.getRightVal() == ((DominoTile) tile).getLeftVal()) {
            tableTiles.add((DominoTile) tile);
        } else if (lastTile.getRightVal() == ((DominoTile) tile).getRightVal()) {
            tableTiles.add(((DominoTile) tile).flip());
        }
    }

    @Override
    public List<DominoSlice> getTableTiles() {
        return tableTiles;
    }

    @Override
    public boolean canPlaceTile(DominoSlice tile) {
        if (tableTiles.isEmpty()) {
            return true;
        }
        DominoSlice firstTile = tableTiles.get(0);
        DominoSlice lastTile = tableTiles.get(tableTiles.size() - 1);
        return (
            tile.getLeftVal() == lastTile.getRightVal() ||
            tile.getRightVal() == lastTile.getRightVal() ||
            tile.getRightVal() == firstTile.getLeftVal() ||
            tile.getLeftVal() == firstTile.getLeftVal()
        );
    }
}
