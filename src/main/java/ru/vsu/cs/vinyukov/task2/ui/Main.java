package ru.vsu.cs.vinyukov.task2.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.vsu.cs.vinyukov.task2.model.DominoPlayer;
import ru.vsu.cs.vinyukov.task2.model.DominoSlice;
import ru.vsu.cs.vinyukov.task2.model.Player;
import ru.vsu.cs.vinyukov.task2.service.DominoGameManager;
import ru.vsu.cs.vinyukov.task2.service.GameManager;

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
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Домино");
        titleLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.BLUEVIOLET);

        Button startButton = new Button("Начать игру");
        startButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 18));
        startButton.setMinSize(150, 50);
        startButton.setStyle("-fx-border-width: 2; -fx-border-color: darkblue; -fx-border-radius: 5px;");
        startButton.setOnAction(e -> handleStartGame());

        nextTurnButton = new Button("Следующий ход");
        nextTurnButton.setId("nextTurnButton");
        nextTurnButton.setDisable(true);
        nextTurnButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 18));
        nextTurnButton.setMinSize(150, 50);
        nextTurnButton.setStyle("-fx-border-width: 2; -fx-border-color: darkgreen; -fx-border-radius: 5px;");
        nextTurnButton.setOnAction(e -> handleNextTurn());

        LinearGradient backgroungGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("EAF2F8")),
                new Stop(1, Color.WHITE)
        );
        root.setBackground(new Background(new BackgroundFill(backgroungGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(titleLabel, gameBoard, startButton, nextTurnButton);
        scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Домино");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleStartGame() {
        gameManager = new DominoGameManager(
                new Player[]{
                        new DominoPlayer(), new DominoPlayer()
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Игра окончена");
            alert.setHeaderText(null);
            alert.setContentText("Партия завершена!");
            alert.showAndWait();
            System.exit(0);
        }
    }

    private void updateUI() {
        gameBoard.getChildren().clear();
        for (DominoSlice tile : gameManager.getGameTable().getTableTiles()) {
            View view = new View(tile);
            gameBoard.getChildren().add(view);
        }
    }
}