package com.sonld.ps.finalflashcard.presentation.flashcard;

import com.sonld.ps.finalflashcard.HelloApplication;
import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.model.TopicResponse;
import com.sonld.ps.finalflashcard.components.DialogCreateFlashCard;
import com.sonld.ps.finalflashcard.components.DialogCreateTopicView;
import com.sonld.ps.finalflashcard.presentation.learning.LearningPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlashcardPresenter implements Initializable {
    @FXML
    private Text topicName;
    @FXML
    private Text totalFlashcard;
    @FXML
    private Text everydayBox;
    @FXML
    private Text everyThreedayBox;
    @FXML
    private Text everyweekBox;
    @FXML
    private Text twoweekBox;
    @FXML
    private Text everymonthBox;
    @FXML
    private AnchorPane learningDisplay;
    @FXML
    private Button startLearningButton;

    private TopicResponse currentTopic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setCurrentTopic(TopicResponse topic) {
        this.currentTopic = topic;
        this.topicName.setText(topic.getName());
        startLearningButton.setOnMouseClicked(mouseEvent -> {
            startLearningButton.setVisible(false);
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("learning.fxml"));
                Parent root = loader.load();
                LearningPresenter learningPresenter = loader.getController();
                learningPresenter.getDataFromTopicId(currentTopic.getId());
                AnchorPane.setTopAnchor(root, 20.0);
                AnchorPane.setRightAnchor(root, 30.0);
                AnchorPane.setBottomAnchor(root, 20.0);
                AnchorPane.setLeftAnchor(root, 30.0);
                learningDisplay.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            var resp = APIInterface.apiService.getTopicInformation(topic.getId()).execute();
            if (resp.isSuccessful()) {
                var topicInformation = resp.body();
                if (topicInformation != null) {
                    this.totalFlashcard.setText(topicInformation.getTotalCard() + " flashcards");
                    var boxInformation = topicInformation.getBoxesInformation();
                    this.everydayBox.setText(boxInformation.getEveryDay() + " flashcards");
                    this.everyThreedayBox.setText(boxInformation.getEveryThreeDays() + " flashcards");
                    this.everyweekBox.setText(boxInformation.getEveryWeek() + " flashcards");
                    this.twoweekBox.setText(boxInformation.getEveryTwoWeeks() + " flashcards");
                    this.everymonthBox.setText(boxInformation.getEveryMonth() + " flashcards");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
