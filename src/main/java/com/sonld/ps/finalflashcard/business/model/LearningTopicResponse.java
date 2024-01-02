package com.sonld.ps.finalflashcard.business.model;

import java.util.List;

public class LearningTopicResponse {
    List<FlashcardResponse> everyday;
    List<FlashcardResponse> everyThreeDays;
    List<FlashcardResponse> everyWeek;
    List<FlashcardResponse> everyTwoWeeks;
    List<FlashcardResponse> everyMonth;
    List<FlashcardResponse> shuffle;

    public List<FlashcardResponse> getEveryday() {
        return everyday;
    }

    public void setEveryday(List<FlashcardResponse> everyday) {
        this.everyday = everyday;
    }

    public List<FlashcardResponse> getEveryThreeDays() {
        return everyThreeDays;
    }

    public void setEveryThreeDays(List<FlashcardResponse> everyThreeDays) {
        this.everyThreeDays = everyThreeDays;
    }

    public List<FlashcardResponse> getEveryWeek() {
        return everyWeek;
    }

    public void setEveryWeek(List<FlashcardResponse> everyWeek) {
        this.everyWeek = everyWeek;
    }

    public List<FlashcardResponse> getEveryTwoWeeks() {
        return everyTwoWeeks;
    }

    public void setEveryTwoWeeks(List<FlashcardResponse> everyTwoWeeks) {
        this.everyTwoWeeks = everyTwoWeeks;
    }

    public List<FlashcardResponse> getEveryMonth() {
        return everyMonth;
    }

    public void setEveryMonth(List<FlashcardResponse> everyMonth) {
        this.everyMonth = everyMonth;
    }

    public List<FlashcardResponse> getShuffle() {
        return shuffle;
    }

    public void setShuffle(List<FlashcardResponse> shuffle) {
        this.shuffle = shuffle;
    }
}
