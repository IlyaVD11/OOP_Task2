package ru.vsu.cs.vinyukov.task2;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;


public class View extends StackPane {
    private Label label;

    public View(DominoSlice domino) {
        setPrefSize(50, 50);
        label = new Label(domino.toString());
        label.setStyle("-fx-font-size: 16px;");
        getChildren().add(label);
    }
}
