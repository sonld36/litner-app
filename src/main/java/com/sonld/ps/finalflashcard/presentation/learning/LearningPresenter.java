package com.sonld.ps.finalflashcard.presentation.learning;

import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.model.FlashcardResponse;
import com.sonld.ps.finalflashcard.business.model.LearningTopicResponse;
import com.sonld.ps.finalflashcard.business.model.SessionLearningState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LearningPresenter implements Initializable {
    @FXML
    private Button notesButton;
    @FXML
    private Button wrongButton;
    @FXML
    private Button correctButton;
    @FXML
    private AnchorPane flashcardDisplay;
    private SessionLearningState sessionLearningState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getDataFromTopicId(String topicId) {
        var flashcardInTopic = getTopicInformation(topicId);
        if (flashcardInTopic != null) {
            var flashcard = flashcardInTopic.getShuffle();
            if (flashcard.isEmpty()) return;
            sessionLearningState = new SessionLearningState(topicId);
            var currentCard = flashcard.get(sessionLearningState.getCurrentIndexCard());
            var flashcardCurrentItem = currentCard.toCard();
//            flashcardCurrentItem.setTranslateX(35);
//            flashcardCurrentItem.setTranslateY(20);
            flashcardDisplay.getChildren().add(flashcardCurrentItem);


            correctButton.setOnMouseClicked((e) -> {
                int currentIndex = sessionLearningState.getCurrentIndexCard();
                sessionLearningState.addFlashcardLearningSession(flashcard.get(currentIndex), true);
                if (currentIndex < flashcard.size()) {
                    sessionLearningState.setCurrentIndexCard(currentIndex + 1);
                }
            });
            wrongButton.setOnMouseClicked((e) -> {
                sessionLearningState.addFlashcardLearningSession(flashcard.get(sessionLearningState.getCurrentIndexCard()), false);
                int currentIndex = sessionLearningState.getCurrentIndexCard();
                flashcard.add(flashcard.get(currentIndex));
                if (currentIndex < flashcard.size()) {
                    sessionLearningState.setCurrentIndexCard(currentIndex + 1);
                }
            });

            sessionLearningState.currentIndexCardProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.intValue() >= 0 && newValue.intValue() < flashcard.size()) {
                    flashcardDisplay.getChildren().clear();
                    var currentFlashcard = flashcard.get(newValue.intValue());
                    var flashcardItem = currentFlashcard.toCard();

                    flashcardDisplay.getChildren().add(flashcardItem);
                } else {
                    flashcardDisplay.getChildren().clear();
                    var flashcardItem = new VBox();
                    flashcardItem.setPrefSize(400, 300);
                    flashcardItem.setStyle("-fx-background-color: #f5f5f5");
                    Text text = new Text("You have finished learning this topic");
                    Button finishButton = finishButton(topicId);
                    finishButton.setTranslateX(150);
                    text.setFont(Font.font("Arial", 20));
                    text.setTextAlignment(TextAlignment.CENTER);
                    text.setWrappingWidth(350);
                    flashcardItem.setTranslateX(35);
                    flashcardItem.setTranslateY(120);
                    flashcardItem.getChildren().addAll(text, finishButton);
                    flashcardItem.setSpacing(10);
                    flashcardDisplay.getChildren().add(flashcardItem);
                    wrongButton.setVisible(false);
                    correctButton.setVisible(false);
                    notesButton.setVisible(false);
                }

            });



        }
    }

    private LearningTopicResponse getTopicInformation(String topicId) {
        try {
            var resp = APIInterface.apiService.getLearningTopic(topicId).execute();
            if (resp.isSuccessful()) {
                return resp.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }





    private Button finishButton(String topicId) {
        Button finishButton = new Button("Finish");
        finishButton.setOnMouseClicked((e) -> {
            sessionLearningState.setTimeEnd();
            try {
                var resp = APIInterface
                        .apiService.learnDone(topicId, sessionLearningState.ofResult()).execute();
                if (resp.isSuccessful()) {
                    finishButton.setDisable(true);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return finishButton;
    }
}
