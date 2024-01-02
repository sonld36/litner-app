package com.sonld.ps.finalflashcard.presentation.topic;

import com.sonld.ps.finalflashcard.HelloApplication;
import com.sonld.ps.finalflashcard.business.model.Pagination;
import com.sonld.ps.finalflashcard.business.model.TopicResponse;
import com.sonld.ps.finalflashcard.business.service.TopicService;
import com.sonld.ps.finalflashcard.presentation.flashcard.FlashcardPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class TopicPresenter implements Initializable {

    @FXML
    private ScrollPane topicTable;
    @FXML
    private VBox topicContainer;
    private TopicService topicService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topicService = new TopicService();
        topicContainer.setSpacing(20);
        topicContainer.setPadding(new Insets(10));
        try {
            Pagination<TopicResponse> pagination = topicService.getAllTopic();
            if (pagination != null) {
                List<TopicResponse> data = pagination.getData();
                var topicItems = data.stream().map(topicResponse -> {
                    var topicButton = topicResponse.createTopic();
                    topicButton.setOnMouseClicked(mouseEvent -> {
                        try {
                            setActionForTopicButton(topicResponse);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return topicButton;
                }).toList();
                topicContainer.getChildren().addAll(topicItems);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setActionForTopicButton(TopicResponse topicButton) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("flashcard.fxml"));
            Parent root = loader.load();
            FlashcardPresenter flashcardPresenter = loader.getController();
            flashcardPresenter.setCurrentTopic(topicButton);
            AnchorPane parent = (AnchorPane) topicTable.getParent();
            parent.getChildren().clear();
            parent.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
