package com.sonld.ps.finalflashcard.business.api;

import com.sonld.ps.finalflashcard.business.model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {
    APIInterface apiService = APIClient.apiClient.create(APIInterface.class);

    @POST("topic")
    Call<Integer> createTopic(@Body TopicCreateRequest topic);

    @GET("topic")
    Call<Pagination<TopicResponse>> getTopics(@Query("page") int page);

    @GET("topic/{id}")
    Call<TopicInformationResponse> getTopicInformation(@Path("id") String id);

    @POST("flashcard")
    Call<Integer> createFlashCard(@Body FlashcardCreateRequest request);

    @GET("topic/learn/{topicId}")
    Call<LearningTopicResponse> getLearningTopic(@Path("topicId") String topicId);

    @POST("topic/learn-done/{topicId}")
    Call<Void> learnDone(@Path("topicId") String topicId, @Body ResultLearningSessionDTO result);
}
