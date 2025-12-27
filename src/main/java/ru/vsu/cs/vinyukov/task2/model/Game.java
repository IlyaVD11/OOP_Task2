package ru.vsu.cs.vinyukov.task2.model;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class Game {
    public static Queue<Player> playerQueue;
    public static GameTable gameTable;
    public static List<DominoSlice> reserve;
    public static Map<Player, List<DominoSlice>> playerWithTiles;
}
