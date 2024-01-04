package com.sonld.ps.finalflashcard.components;

import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.model.TopicCreateRequest;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DialogCreateTopicView extends Dialog<Boolean> {
    private final TextField nameField = new TextField();
    private final TextField descriptionField = new TextField();
    private final TextField categoryField = new TextField();

    public DialogCreateTopicView() {
        Label nameLabel = new Label("Topic Name");
        Label descriptionLabel = new Label("Topic Description");
        Label categoryLabel = new Label("Topic Category");
        nameField.setText("");
        descriptionField.setText("");
        categoryField.setText("");

        VBox vBox = new VBox(nameLabel, nameField, descriptionLabel, descriptionField, categoryLabel, categoryField);
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(40));

        DialogPane dp = getDialogPane();

        setTitle("Create Topic");
        setResultConverter((e) -> {
            TopicCreateRequest topicCreateRequest = null;
            if (e.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                topicCreateRequest = new TopicCreateRequest(nameField.getText(), descriptionField.getText(), new String[]{categoryField.getText()});
                try {
                    var res = APIInterface.apiService.createTopic(topicCreateRequest).execute();
                    Alert alert;
                    if (res.isSuccessful()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Topic created successfully");
                        alert.showAndWait();
                        return true;
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Topic created error");
                        alert.showAndWait();
                        return false;
                    }

                } catch (IOException ex) {
                    return false;
                }
            } else return false;
        });

        this.setTitle("Create Topic");
        ButtonType bt = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(bt, ButtonType.CANCEL);
        dp.setContent(vBox);

    }
}
