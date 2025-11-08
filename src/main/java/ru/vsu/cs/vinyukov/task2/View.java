package ru.vsu.cs.vinyukov.task2;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class View extends StackPane {
    private Label label;

    public View(DominoSlice slice) {
        setPrefSize(50, 50);
        label = new Label(slice.toString());
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        getChildren().add(label);
    }
}
