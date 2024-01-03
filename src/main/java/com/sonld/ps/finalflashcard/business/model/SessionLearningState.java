package com.sonld.ps.finalflashcard.business.model;

import com.google.gson.annotations.JsonAdapter;
import com.sonld.ps.finalflashcard.business.common.DateConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionLearningState implements Serializable {

    List<FlashcardLearningSession> flashcardLearningSessionDTOList;
    private String topicId;
    private IntegerProperty timesCorrect;
    private IntegerProperty timesIncorrect;

    private Instant timeStart;

    private Instant timeEnd;
    private IntegerProperty totalCard;

    private IntegerProperty currentIndexCard;

    public SessionLearningState(String topicId) {
        this.topicId = topicId;
        this.timesCorrect = new SimpleIntegerProperty(0);
        this.timesIncorrect = new SimpleIntegerProperty(0);
        this.totalCard = new SimpleIntegerProperty(0);
        this.flashcardLearningSessionDTOList = new ArrayList<>();
        this.timeStart = new Date().toInstant();
        this.currentIndexCard = new SimpleIntegerProperty(0);
    }

    public void addFlashcardLearningSession(FlashcardResponse flashcard, boolean isCorrect) {
        totalCard.set(totalCard.get() + 1);
        if (isCorrect) {
            this.timesCorrect.set(this.timesCorrect.get() + 1);
        } else {
            this.timesIncorrect.set(this.timesIncorrect.get() + 1);
        }
        FlashcardLearningSession flashcardLearningSession = new FlashcardLearningSession(flashcard.getId(), isCorrect, new Date().toInstant());
        this.flashcardLearningSessionDTOList.add(flashcardLearningSession);
    }

    public void setTimeEnd() {
        this.timeEnd = new Date().toInstant();
    }

    public int getTimesCorrect() {
        return timesCorrect.get();
    }

    public IntegerProperty timesCorrectProperty() {
        return timesCorrect;
    }

    public void setTimesCorrect(int timesCorrect) {
        this.timesCorrect.set(timesCorrect);
    }

    public int getTimesIncorrect() {
        return timesIncorrect.get();
    }

    public IntegerProperty timesIncorrectProperty() {
        return timesIncorrect;
    }

    public void setTimesIncorrect(int timesIncorrect) {
        this.timesIncorrect.set(timesIncorrect);
    }

    public int getTotalCard() {
        return totalCard.get();
    }

    public IntegerProperty totalCardProperty() {
        return totalCard;
    }

    public void setTotalCard(int totalCard) {
        this.totalCard.set(totalCard);
    }

    public int getCurrentIndexCard() {
        return currentIndexCard.get();
    }

    public IntegerProperty currentIndexCardProperty() {
        return currentIndexCard;
    }

    public void setCurrentIndexCard(int currentIndexCard) {
        this.currentIndexCard.set(currentIndexCard);
    }

    public ResultLearningSessionDTO ofResult() {
        ResultLearningSessionDTO resultLearningSessionDTO = new ResultLearningSessionDTO();
        resultLearningSessionDTO.setFlashcardLearningSessionDTOList(this.flashcardLearningSessionDTOList);
        resultLearningSessionDTO.setTimesCorrect(this.timesCorrect.get());
        resultLearningSessionDTO.setTimesIncorrect(this.timesIncorrect.get());
        resultLearningSessionDTO.setTotalCard(this.totalCard.get());
        resultLearningSessionDTO.setTimeStart(this.timeStart);
        resultLearningSessionDTO.setTimeEnd(this.timeEnd);
        resultLearningSessionDTO.setTopicId(this.topicId);
        return resultLearningSessionDTO;
    }

    public static class FlashcardLearningSession {
        private String id;
        private boolean isCorrect;

        @JsonAdapter(DateConverter.class)
        private Instant timeAnswer;

        public FlashcardLearningSession(String id, boolean isCorrect, Instant timeAnswer) {
            this.id = id;
            this.isCorrect = isCorrect;
            this.timeAnswer = timeAnswer;
        }
    }
}
