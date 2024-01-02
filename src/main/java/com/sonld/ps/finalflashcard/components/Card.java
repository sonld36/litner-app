package com.sonld.ps.finalflashcard.components;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Card extends StackPane {
    private boolean isFront = true;

    public Card() {
        super();
    }

    public Card(Node front, Node back) {
        super();

        Rectangle card = new Rectangle(300, 250);
        card.setFill(Color.LIGHTBLUE); // Set the background color
        card.setStroke(Color.DARKBLUE); // Set the border color
        card.setStrokeWidth(2); // Set the border width
        card.setArcWidth(20); // Set the horizontal corner radius
        card.setArcHeight(20);

        StackPane.setAlignment(front, Pos.CENTER);
        StackPane.setAlignment(back, Pos.CENTER);
        StackPane.setAlignment(card, Pos.CENTER);
        this.getChildren().addAll(card, front, back);

        RotateTransition flip = new RotateTransition(Duration.millis(400), card);
        flip.setAxis(new Point3D(0, 1, 0)); // Rotate around Y-axis
        flip.setFromAngle(0);
        flip.setToAngle(180);
        flip.setInterpolator(Interpolator.EASE_BOTH);

        flip.setOnFinished(event -> {
            front.setVisible(isFront);
            back.setVisible(!isFront);
        });

        this.setOnMouseClicked(event -> {
            flipCard();
            front.setVisible(false);
            back.setVisible(false);
            flip.play();

        });
    }

    private void flipCard() {
        this.isFront = !isFront;
    }
}
