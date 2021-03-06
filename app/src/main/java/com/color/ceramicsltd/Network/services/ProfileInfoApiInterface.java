package com.color.ceramicsltd.Network.services;

import com.color.ceramicsltd.Network.response.ProfileInfoUpdateResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProfileInfoApiInterface {
    @POST("user/info/update")
    Call<ProfileInfoUpdateResponse> updateProfileInfo(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}
