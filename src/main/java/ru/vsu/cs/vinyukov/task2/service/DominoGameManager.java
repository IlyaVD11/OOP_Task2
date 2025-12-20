package ru.vsu.cs.vinyukov.task2.service;

import ru.vsu.cs.vinyukov.task2.model.*;

import java.util.*;

import static ru.vsu.cs.vinyukov.task2.model.Game.*;

public class DominoGameManager implements GameManager {

    public DominoGameManager(Player[] players) {
        gameTable = new DefaultGameTable();
        playerQueue = new LinkedList<>();
        for (Player player : players) {
            playerQueue.add(player);
        }
        playerWithTiles = new HashMap<>();
        List<DominoSlice> allTiles = generateTiles();
        distributeTiles(allTiles);
    }

    private void distributeTiles(List<DominoSlice> allTiles) {
        Random random = new Random();
        Collections.shuffle(allTiles, random);

        for (Player player : playerQueue) {
            List<DominoSlice> playerHand = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                playerHand.add(allTiles.remove(0));
            }
            playerWithTiles.put(player, playerHand);
        }
        Game.reserve = allTiles;
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
    public String getActivePlayerName() {
        return playerQueue.peek().getName();
    }

    @Override
    public boolean hasNextMove(GameTable table, Player player) {
        List<DominoSlice> tiles = getPlayerTiles(player);
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
    public DominoSlice chooseNextMove(GameTable table, Player player) {
        List<DominoSlice> tiles = getPlayerTiles(player);
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

    @Override
    public List<DominoSlice> getPlayerTiles(Player player) {
        return playerWithTiles.getOrDefault(player, Collections.emptyList());
    }

    @Override
    public void removeTile(Player player, DominoSlice tile) {
        List<DominoSlice> playerHand = playerWithTiles.get(player);
        if (playerHand != null) {
            playerHand.remove(tile);
        }
    }

    @Override
    public void giveTileToPlayer(Player player, DominoSlice tile) {
        List<DominoSlice> playerHand = playerWithTiles.computeIfAbsent(player, k -> new ArrayList<>());
        playerHand.add(tile);
    }

    @Override
    public void startGame() {
         Player firstPlayer = findBiggestDouble();
         if (firstPlayer != null && !playerWithTiles.get(firstPlayer).isEmpty()) {
             for (DominoSlice tile : playerWithTiles.get(firstPlayer)) {
                 if (tile.isDouble()) {
                     playerWithTiles.get(firstPlayer).remove(tile);
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
            for (DominoSlice tile : playerWithTiles.get(player)) {
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
        boolean madeMove = false;
        Random random = new Random();

        while (!madeMove && !reserve.isEmpty()) {
            if (hasNextMove(getGameTable(), currentPlayer)) {
                DominoSlice tile = chooseNextMove(gameTable, currentPlayer);
                removeTile(currentPlayer, tile);
                gameTable.placeTile(tile);
                madeMove = true;
            } else {
                DominoSlice takenFromReserve = reserve.remove(random.nextInt(reserve.size()));
                giveTileToPlayer(currentPlayer, takenFromReserve);
            }
        }
        playerQueue.offer(currentPlayer);
        return madeMove;
    }

    @Override
    public GameTable getGameTable() {
        return gameTable;
    }

    @Override
    public boolean isGameOver() {
        return playerQueue.stream().anyMatch(p -> playerWithTiles.get(p).isEmpty());
    }
}