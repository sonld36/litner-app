package com.sonld.ps.finalflashcard.components;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Topic extends Label {

    public Topic(String text) {
        super(text);
        this.setMinHeight(60);
        this.setMinWidth(200);
        this.setAlignment(Pos.CENTER);
        this.setFont(Font.font("Arial", 16));
        this.setTextFill(Color.WHITE);
        this.setPrefWidth(Double.MAX_VALUE);


        // Styling with a more vibrant color and rounded corners
        this.setStyle(
                "-fx-background-color: #3498db;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #2980b9;"
        );

        // Create initial animation
        ScaleTransition grow = new ScaleTransition(Duration.millis(100), this);
        grow.setFromX(0);
        grow.setToX(1);
        grow.setFromY(0);
        grow.setToY(1);
        grow.setInterpolator(Interpolator.EASE_IN);

        // Create hover animation
        ScaleTransition hoverGrow = new ScaleTransition(Duration.millis(80), this);
        hoverGrow.setFromX(1);
        hoverGrow.setToX(1.1);
        hoverGrow.setFromY(1);
        hoverGrow.setToY(1.1);
        hoverGrow.setInterpolator(Interpolator.EASE_BOTH);

        // Play initial animation on this display
        this.setVisible(true);
        grow.play();

        // Handle hover events
        this.setOnMouseEntered(event -> hoverGrow.play());
        this.setOnMouseExited(event -> {
            this.setScaleX(1);
            this.setScaleY(1);
        });
    }
}
