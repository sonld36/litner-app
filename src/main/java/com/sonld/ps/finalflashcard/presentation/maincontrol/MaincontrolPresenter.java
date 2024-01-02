package com.sonld.ps.finalflashcard.presentation.maincontrol;

import com.sonld.ps.finalflashcard.HelloApplication;
import com.sonld.ps.finalflashcard.business.model.Pagination;
import com.sonld.ps.finalflashcard.business.model.TopicResponse;
import com.sonld.ps.finalflashcard.business.service.TopicService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        try {
            mainContent.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
