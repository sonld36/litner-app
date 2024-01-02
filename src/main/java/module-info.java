module com.sonld.ps.finalflashcard {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;

    opens com.sonld.ps.finalflashcard to javafx.fxml;
    opens com.sonld.ps.finalflashcard.presentation.maincontrol to javafx.fxml;
    opens com.sonld.ps.finalflashcard.presentation.topic to javafx.fxml;
    opens com.sonld.ps.finalflashcard.presentation.flashcard to javafx.fxml;
    opens com.sonld.ps.finalflashcard.presentation.learning to javafx.fxml;

    opens com.sonld.ps.finalflashcard.business.model to com.google.gson;
    exports com.sonld.ps.finalflashcard;
    exports com.sonld.ps.finalflashcard.components;
    exports com.sonld.ps.finalflashcard.presentation.maincontrol;
    exports com.sonld.ps.finalflashcard.presentation.topic;
    exports com.sonld.ps.finalflashcard.business.common;
}