package ru.vsu.cs.vinyukov.task2.model;


public class DominoPlayer implements Player {
    private String name;

    public DominoPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}