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
    private GameManager gameManager;
    private Button nextTurnButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameBoard = new HBox();
        gameBoard.setSpacing(10);
        gameBoard.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        Button startButton = new Button("Начать игру");
        startButton.setOnAction(e -> handleStartGame());

        nextTurnButton = new Button("Следующий ход");
        nextTurnButton.setId("nextTurnButton");
        nextTurnButton.setDisable(true);
        nextTurnButton.setOnAction(e -> handleNextTurn());

        root.getChildren().addAll(gameBoard, startButton);
        scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Домино");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleStartGame() {
        gameManager = new DominoGameManager(
                new Player[]{
                        new DominoPlayer("Игрок 1"), new DominoPlayer("Игрок 2")
                });
        gameManager.startGame();
        updateUI();
        nextTurnButton.setDisable(false);
    }

    private void handleNextTurn() {
        gameManager.nextTurn();
        updateUI();
        checkEndGame();
    }

    private void checkEndGame() {
        if (gameManager.isGameOver()) {
            System.exit(0);
        }
    }

    private void updateUI() {
        gameBoard.getChildren().clear();
        for (DominoSlice tile : gameManager.getGameTable().getTableTiles()) {
            gameBoard.getChildren().add(new View(tile));
        }
    }
}
