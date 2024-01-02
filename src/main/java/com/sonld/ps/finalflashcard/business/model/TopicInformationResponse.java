package com.sonld.ps.finalflashcard.business.model;

import java.util.List;

public class TopicInformationResponse {
    private String id;
    private int totalCard;
    private BoxInformation boxesInformation;
    private List<FlashcardResponse> flashcards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(int totalCard) {
        this.totalCard = totalCard;
    }

    public BoxInformation getBoxesInformation() {
        return boxesInformation;
    }

    public void setBoxesInformation(BoxInformation boxesInformation) {
        this.boxesInformation = boxesInformation;
    }

    public List<FlashcardResponse> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<FlashcardResponse> flashcards) {
        this.flashcards = flashcards;
    }

    public static class BoxInformation {
        private int everyDay;
        private int everyThreeDays;
        private int everyWeek;
        private int everyTwoWeeks;
        private int everyMonth;

        public int getEveryDay() {
            return everyDay;
        }

        public void setEveryDay(int everyDay) {
            this.everyDay = everyDay;
        }

        public int getEveryThreeDays() {
            return everyThreeDays;
        }

        public void setEveryThreeDays(int everyThreeDays) {
            this.everyThreeDays = everyThreeDays;
        }

        public int getEveryWeek() {
            return everyWeek;
        }

        public void setEveryWeek(int everyWeek) {
            this.everyWeek = everyWeek;
        }

        public int getEveryTwoWeeks() {
            return everyTwoWeeks;
        }

        public void setEveryTwoWeeks(int everyTwoWeeks) {
            this.everyTwoWeeks = everyTwoWeeks;
        }

        public int getEveryMonth() {
            return everyMonth;
        }

        public void setEveryMonth(int everyMonth) {
            this.everyMonth = everyMonth;
        }
    }
}
