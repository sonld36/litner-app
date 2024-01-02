package com.sonld.ps.finalflashcard.business.model;

import com.sonld.ps.finalflashcard.business.common.Constant;

import java.util.List;

public class FlashcardCreateRequest {
    private String answer;

    private String question;
    private Constant.TypeTextFlashCard type;
    private String topicId;
    private List<String> notes;

    public FlashcardCreateRequest(String answer, String question, Constant.TypeTextFlashCard type, String topicId) {
        this.answer = answer;
        this.question = question;
        this.type = type;
        this.topicId = topicId;
    }
}
