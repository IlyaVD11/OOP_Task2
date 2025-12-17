package ru.vsu.cs.vinyukov.task2.service;

import ru.vsu.cs.vinyukov.task2.model.*;

import java.util.*;

public class DominoGameManager implements GameManager {
    private Queue<Player> playerQueue;
    private GameTable gameTable;
    private List<DominoSlice> reserve;
    private Map<Player, List<DominoSlice>> playerWithTiles;

    public DominoGameManager(Player[] players) {
        gameTable = new DefaultGameTable();
        playerQueue = new LinkedList<>();
        for (Player player : players) {
            playerQueue.add(player);
            if (player instanceof DominoPlayer) {
                ((DominoPlayer) player).setGameManager(this);
            }
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
        reserve = allTiles;
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
            if (currentPlayer.hasNextMove(gameTable)) {
                DominoSlice tile = currentPlayer.chooseNextMove(gameTable);
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