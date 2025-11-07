package ru.vsu.cs.vinyukov.task2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private HBox gameBoard;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameBoard = new HBox();
        gameBoard.getSpacing();
        gameBoard.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        Button startButton = new Button("Начать игру");
        startButton.setOnAction(e -> handleStartGame());

        root.getChildren().addAll(gameBoard, startButton);
        scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Домино");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleStartGame() {
        System.out.println("Начало игры");
        GameManager manager = new DominoGameManager(
                new Player[]{
                        new DominoPlayer("Игрок 1"), new DominoPlayer("Игрок 2")
                });
        manager.startGame();
    }
}
