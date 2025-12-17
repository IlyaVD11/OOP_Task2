package ru.vsu.cs.vinyukov.task2.ui;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ru.vsu.cs.vinyukov.task2.model.DominoSlice;


public class View extends StackPane {
    private Label label;

    public View(DominoSlice slice) {
        double width = 100;
        double height = 50;
        setPrefSize(width, height);

        Rectangle borderRect = new Rectangle(width, height);
        borderRect.setArcWidth(10);
        borderRect.setArcHeight(10);
        borderRect.setFill(Color.LIGHTGREY);
        borderRect.setStroke(Color.STEELBLUE);
        borderRect.setStrokeWidth(2);
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.GRAY, 5, 0.5, 0, 0);
        borderRect.setEffect(dropShadow);

        label = new Label(slice.toString());
        label.setFont(Font.font("Arial Black", FontWeight.BOLD, 16));
        label.setTextFill(Color.BLACK);
        label.setTranslateY(-height / 4);

        getChildren().addAll(borderRect, label);
    }
}
