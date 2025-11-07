package ru.vsu.cs.vinyukov.task2;

import java.util.*;

public class DominoGameManager implements GameManager{
    private Queue<Player> playerQueue;
    private GameTable gameTable;

    public DominoGameManager(Player[] players) {
        gameTable = new DefaultGameTable();
        playerQueue = new LinkedList<>();
        for (Player player : players) {
            playerQueue.add(player);
        }
        distributeTiles(generateTiles());
    }

    private void distributeTiles(List<DominoSlice> allTiles) {
        Random random = new Random();
        List<DominoSlice> shuffledTiles = new ArrayList<>(allTiles);
        Collections.shuffle(shuffledTiles, random);

        int index = 0;
        while (index < shuffledTiles.size()) {
            for (Player player : playerQueue) {
                if (index < shuffledTiles.size()) {
                    player.addTile(shuffledTiles.get(index++));
                }
            }
        }
    }

    private List<DominoSlice> generateTiles() {
        List<DominoSlice> tiles = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                tiles.add(new DominoTile(i, j));
            }
        }
        return tiles;
    }

    @Override
    public void startGame() {
         Player firstPlayer = findBiggestDouble();
         if (firstPlayer != null && !firstPlayer.getTiles().isEmpty()) {
             for (DominoSlice tile : firstPlayer.getTiles()) {
                 if (tile.isDouble()) {
                     firstPlayer.getTiles().remove(tile);
                     gameTable.placeTile(tile);
                     break;
                 }
             }
         }
    }

    private Player findBiggestDouble() {
        Player playerWithDouble = null;
        int maxVal = -1;
        for (Player player : playerQueue) {
            for (DominoSlice tile : player.getTiles()) {
                if (tile.isDouble() && tile.getLeftVal() > maxVal) {
                    maxVal = tile.getLeftVal();
                    playerWithDouble = player;
                }
            }
        }
        return playerWithDouble;
    }

    @Override
    public boolean nextTurn() {
        Player currentPlayer = playerQueue.poll();
        if (currentPlayer.hasNextMove(gameTable)) {
            DominoSlice chosenTile = currentPlayer.chooseNextMove(gameTable);
            if (chosenTile != null) {
                currentPlayer.getTiles().remove(chosenTile);
                gameTable.placeTile(chosenTile);
            }
        }
        playerQueue.add(playerQueue.poll());
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
        return playerQueue.stream().anyMatch(p -> p.getTiles().isEmpty());
    }
}
