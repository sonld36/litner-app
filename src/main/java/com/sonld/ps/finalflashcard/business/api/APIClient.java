package com.sonld.ps.finalflashcard.business.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface APIClient {
    Retrofit apiClient = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://leitner-o6u2tfrixq-uc.a.run.app/api/")
            .baseUrl("http://localhost:8080/api/")
            .build();
}
