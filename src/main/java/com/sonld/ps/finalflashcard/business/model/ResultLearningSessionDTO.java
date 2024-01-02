package com.sonld.ps.finalflashcard.business.model;

import com.google.gson.annotations.JsonAdapter;

import java.time.Instant;
import java.util.List;

public class ResultLearningSessionDTO {
    List<SessionLearningState.FlashcardLearningSession> flashcardLearningSessionDTOList;
    private String topicId;
    private int timesCorrect;
    private int timesIncorrect;

//    @JsonAdapter(DateConverter.class)
    private Instant timeStart;

//    @JsonAdapter(DateConverter.class)
    private Instant timeEnd;
    private int totalCard;

    public ResultLearningSessionDTO() {
    }

    public void setFlashcardLearningSessionDTOList(List<SessionLearningState.FlashcardLearningSession> flashcardLearningSessionDTOList) {
        this.flashcardLearningSessionDTOList = flashcardLearningSessionDTOList;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public void setTimesCorrect(int timesCorrect) {
        this.timesCorrect = timesCorrect;
    }

    public void setTimesIncorrect(int timesIncorrect) {
        this.timesIncorrect = timesIncorrect;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setTotalCard(int totalCard) {
        this.totalCard = totalCard;
    }

    public List<SessionLearningState.FlashcardLearningSession> getFlashcardLearningSessionDTOList() {
        return flashcardLearningSessionDTOList;
    }

    public String getTopicId() {
        return topicId;
    }

    public int getTimesCorrect() {
        return timesCorrect;
    }

    public int getTimesIncorrect() {
        return timesIncorrect;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public int getTotalCard() {
        return totalCard;
    }
}
