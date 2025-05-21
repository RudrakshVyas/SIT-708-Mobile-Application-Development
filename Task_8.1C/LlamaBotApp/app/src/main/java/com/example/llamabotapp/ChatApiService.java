package com.example.llamabotapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatApiService {
    @Headers("Content-Type: application/json")
    @POST("/chat")
    Call<ChatResponse> sendMessage(@Body ChatRequest request);
}
