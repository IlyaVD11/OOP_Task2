package ru.vsu.cs.vinyukov.task2;

import java.util.*;

public class DominoGameManager implements GameManager{
    private Queue<Player> playerQueue;
    private GameTable gameTable;
    private List<DominoSlice> reserve;

    public DominoGameManager(Player[] players) {
        gameTable = new DefaultGameTable();
        playerQueue = new LinkedList<>();
        for (Player player : players) {
            playerQueue.add(player);
        }
        List<DominoSlice> allTiles = generateTiles();
        distributeTiles(allTiles);
    }

    private void distributeTiles(List<DominoSlice> allTiles) {
        Random random = new Random();
        Collections.shuffle(allTiles, random);

        for (int i = 0; i < 7 * playerQueue.size(); i++) {
            playerQueue.element().addTile(allTiles.remove(0));
            playerQueue.offer(playerQueue.poll());
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
        boolean madeMove = false;
        Random random = new Random();

        while (!madeMove && !reserve.isEmpty()) {
            if (currentPlayer.hasNextMove(gameTable)) {
                DominoSlice tile = currentPlayer.chooseNextMove(gameTable);
                currentPlayer.getTiles().remove(tile);
                gameTable.placeTile(tile);
                madeMove = true;
            } else {
                DominoSlice takenFromReserve = reserve.remove(random.nextInt(reserve.size()));
                currentPlayer.addTile(takenFromReserve);
            }
        }
        playerQueue.offer(currentPlayer);
        return madeMove;
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
