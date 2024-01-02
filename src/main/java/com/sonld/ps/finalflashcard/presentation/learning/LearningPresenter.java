package com.sonld.ps.finalflashcard.presentation.learning;

import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.model.FlashcardResponse;
import com.sonld.ps.finalflashcard.business.model.LearningTopicResponse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
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
    private Pane flashcardDisplay;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getDataFromTopicId(String topicId) {
        var flashcardInTopic = getTopicInformation(topicId);
        if (flashcardInTopic != null) {
            var flashcard = flashcardInTopic.getShuffle();
            if (flashcard.isEmpty()) return;
            Iterator<FlashcardResponse> iterator = flashcard.iterator();
            var flashcardResponse = iterator.next();
            var flashcardItem = flashcardResponse.toCard(new Text(flashcardResponse.getQuestion()), new Text(flashcardResponse.getAnswer()));
            flashcardDisplay.getChildren().add(flashcardItem);
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
}
