package com.sonld.ps.finalflashcard.components;

import com.sonld.ps.finalflashcard.HelloApplication;
import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.common.Constant;
import com.sonld.ps.finalflashcard.business.model.FlashcardCreateRequest;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DialogCreateFlashCard extends Dialog<FlashcardCreateRequest> {
    private final TextField questionTextField = new TextField();
    private final TextField answerTextField = new TextField();

    private Button uploadImage;
    private Text imageName;
    private byte[] imageByte;

    public DialogCreateFlashCard(String topicId) {
        Label questionLabel = new Label("Question");
        Label answerLabel = new Label("Answer");
        uploadImage = createButtonUploadImage();
        ButtonBar buttonBar = new ButtonBar();
        HBox hbox = new HBox(answerLabel, buttonBar);
        HBox.setHgrow(buttonBar, Priority.ALWAYS);
        questionTextField.setText("");
        answerTextField.setText("");
        imageName = new Text("No file chosen");
        imageName.setWrappingWidth(200);
        HBox answerBox = new HBox(answerTextField, uploadImage);
        HBox.setHgrow(answerTextField, Priority.ALWAYS);
        answerBox.setSpacing(5);

        ButtonBar removeImage = new ButtonBar();
        Button removeImageButton = new Button("X");
        removeImageButton.setMaxWidth(20);
        removeImage.setPrefWidth(20);
        removeImage.getButtons().add(removeImageButton);
        removeImageButton.setOnMouseClicked(event -> {
            imageName.setText("No file chosen");
            imageByte = null;
        });
        HBox informationBox = new HBox(imageName, removeImage);
        informationBox.setSpacing(5);
        HBox.setHgrow(informationBox, Priority.ALWAYS);

        VBox vBox = new VBox(questionLabel, questionTextField, hbox, answerBox, informationBox);
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(40));

        DialogPane dp = getDialogPane();
        setTitle("Create Flashcard");
        setResultConverter((e) -> {
            FlashcardCreateRequest flashCard = new FlashcardCreateRequest(answerTextField.getText(), questionTextField.getText(), imageByte, topicId);
            if (e.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                try {
                    var response = APIInterface.apiService.createFlashCard(flashCard).execute();
                    Alert alert;
                    if (response.isSuccessful()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Flashcard created successfully");
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Flashcard created error");
                    }
                    alert.showAndWait();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return flashCard;
        });
        ButtonType bt = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(bt, ButtonType.CANCEL);
        dp.setContent(vBox);

    }

    private Button createButtonUploadImage() {
        File image = new File(HelloApplication.class.getResource("/images/picture-icon.png").getFile());
        ImageView imageView = new ImageView(image.toURI().toString());
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Button button = new Button("", imageView);
        button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                String nameFile = selectedFile.getName();
                this.imageByte = fileToByteArray(selectedFile);
                imageName.setText(nameFile);
            }
        });
        return button;
    }

    public byte[] fileToByteArray(File file) {
        byte[] bArray = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            bArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bArray;
    }
}
