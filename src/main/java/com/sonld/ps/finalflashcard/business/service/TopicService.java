package com.sonld.ps.finalflashcard.business.service;

import com.sonld.ps.finalflashcard.business.api.APIInterface;
import com.sonld.ps.finalflashcard.business.model.Pagination;
import com.sonld.ps.finalflashcard.business.model.TopicResponse;

import java.io.IOException;

public class TopicService {
    public Pagination<TopicResponse> getAllTopic() throws IOException {
        var resp = APIInterface.apiService.getTopics(1).execute();
        if (resp.isSuccessful()) {
            return resp.body();
        }
        return null;
    }
}
