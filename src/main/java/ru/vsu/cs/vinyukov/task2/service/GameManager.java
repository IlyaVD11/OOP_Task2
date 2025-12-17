package ru.vsu.cs.vinyukov.task2.service;

import ru.vsu.cs.vinyukov.task2.model.DominoSlice;
import ru.vsu.cs.vinyukov.task2.model.GameTable;
import ru.vsu.cs.vinyukov.task2.model.Player;

import java.util.List;

public interface GameManager {
    void startGame();
    boolean nextTurn();
    GameTable getGameTable();
    boolean isGameOver();
    List<DominoSlice> getPlayerTiles(Player player);
    void removeTile(Player player, DominoSlice tile);
    void giveTileToPlayer(Player player, DominoSlice tile);
}