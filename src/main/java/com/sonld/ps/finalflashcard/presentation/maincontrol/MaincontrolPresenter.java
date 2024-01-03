package com.sonld.ps.finalflashcard.presentation.maincontrol;

import com.sonld.ps.finalflashcard.HelloApplication;
import com.sonld.ps.finalflashcard.business.model.Pagination;
import com.sonld.ps.finalflashcard.business.model.TopicResponse;
import com.sonld.ps.finalflashcard.business.service.TopicService;
import com.sonld.ps.finalflashcard.components.DialogCreateTopicView;
import com.sonld.ps.finalflashcard.presentation.topic.TopicPresenter;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MaincontrolPresenter implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button createButton;
    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("topic.fxml"));
        backButton.setDisable(true);
        backButton.setOnMouseClicked(this::setActionForBackButton);
        createButton.setText("Create Topic");
        createButton.setOnMouseClicked(event -> {
            DialogCreateTopicView dialogCreateTopicView = new DialogCreateTopicView();
            dialogCreateTopicView.showAndWait();
        });
        try {
            mainContent.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainContent.getChildren().addListener((ListChangeListener<Node>) change -> {
            if (!mainContent.getChildren().isEmpty()) {
                AnchorPane mainContentChildren = (AnchorPane) mainContent.getChildren().get(0);
                Stage stage = (Stage) mainContent.getScene().getWindow();
                stage.sizeToScene();
                var child = mainContentChildren.getChildren().get(0);
                if (child instanceof ScrollPane) {
                    backButton.setDisable(true);
                    createButton.setText("Create Topic");
                    createButton.setOnMouseClicked(event -> {
                        DialogCreateTopicView dialogCreateTopicView = new DialogCreateTopicView();
                        dialogCreateTopicView.showAndWait();
                    });
                } else {
                    backButton.setDisable(false);
                    createButton.setText("Create Flashcard");
                }
            }
        });
    }

    private void setActionForBackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("topic.fxml"));
            mainContent.getChildren().clear();
            mainContent.getChildren().add(fxmlLoader.load());
            backButton.setDisable(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
