package com.color.ceramicsltd.Network.services;

import com.color.ceramicsltd.Network.response.RegistrationResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/signup")
    Call<RegistrationResponse> sendResgisterRequest(@Body JsonObject jsonObject);
}
