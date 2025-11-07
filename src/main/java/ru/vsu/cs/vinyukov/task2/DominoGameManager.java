package ru.vsu.cs.vinyukov.task2;

import java.util.LinkedList;
import java.util.Queue;

public class DominoGameManager implements GameManager{
    private Queue<Player> playerQueue;
    private GameTable gameTable;

    public DominoGameManager(Player[] players) {
        gameTable = new DefaultGameTable();
        playerQueue = new LinkedList<>();
        for (Player player : players) {
            playerQueue.add(player);
        }
        distributeTiles();
    }

    private void distributeTiles() {
        Queue<DominoSlice> allTiles = generateTiles();
        while (!allTiles.isEmpty()) {
            for (Player player : playerQueue) {
                if (!allTiles.isEmpty()) {
                    player.addTile(allTiles.remove());
                }
            }
        }
    }

    private static Queue<DominoSlice> generateTiles() {
        Queue<DominoSlice> tiles = new LinkedList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                tiles.add(new DominoTile(i, j));
            }
        }
        return tiles;
    }

    @Override
    public void startGame() {
        System.out.println("Игра началась!");
        Player firstPlayer = playerQueue.peek();
        DominoSlice firstTile = firstPlayer.getTiles().remove(0);
        gameTable.placeTile(firstTile);
        System.out.println("Первый игрок положил первую костяшку");
    }

    @Override
    public boolean nextTurn() {
        Player currentPlayer = playerQueue.poll();
        playerQueue.add(currentPlayer);
        return true;
    }

    @Override
    public Player getCurrentPlayer() {
        return playerQueue.peek();
    }

    @Override
    public GameTable getGameTable() {
        return gameTable;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
