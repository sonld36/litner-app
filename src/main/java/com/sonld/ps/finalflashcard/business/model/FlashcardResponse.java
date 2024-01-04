package com.sonld.ps.finalflashcard.business.model;

import com.google.gson.annotations.JsonAdapter;
import com.sonld.ps.finalflashcard.business.common.Base64DataAdapter;
import com.sonld.ps.finalflashcard.business.common.Constant;
import com.sonld.ps.finalflashcard.components.Card;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FlashcardResponse {
    private String id;
    private String question;
    private String answer;

    @JsonAdapter(Base64DataAdapter.class)
    private byte[] image;
    private String topicId;
    private Constant.BoxLevel boxLevel;
    private Date lastReview;
    private String[] notes;

//    @Override
//    public String getName() {
//        return null;
//    }

    public String getId() {
        return id;
    }

//    @Override
////    public Button createButton(GlobalState globalState) {
////        return new ButtonComponent(this.getQuestion());
////    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Constant.BoxLevel getBoxLevel() {
        return boxLevel;
    }

    public void setBoxLevel(Constant.BoxLevel boxLevel) {
        this.boxLevel = boxLevel;
    }

    public Date getLastReview() {
        return lastReview;
    }

    public void setLastReview(Date lastReview) {
        this.lastReview = lastReview;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public Card toCard() {
        ImageView imageCurrentView = createImageView(this.getImage());
        VBox answerCurrentNode = imageCurrentView != null ? new VBox(imageCurrentView, createText(this.getAnswer())) : new VBox(createText(this.getAnswer()));
        answerCurrentNode.setSpacing(10);
        return new Card(createText(this.getQuestion()), answerCurrentNode);
    }

    private Text createText(String text) {
        Text textNode = new Text(text);
        textNode.setWrappingWidth(350);
        textNode.setTextAlignment(TextAlignment.JUSTIFY);
        textNode.setFont(Font.font("Arial", 15));
        return textNode;
    }

    private ImageView createImageView(byte[] image) {
        if (image == null) return null;
        InputStream is = new ByteArrayInputStream(image);
        Image img = new Image(is);
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(280);
        imageView.setFitWidth(370);;
        return imageView;
    }
}
