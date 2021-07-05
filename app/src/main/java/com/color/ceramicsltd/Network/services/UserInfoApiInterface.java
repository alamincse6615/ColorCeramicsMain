package com.color.ceramicsltd.Network.services;

import com.color.ceramicsltd.Network.response.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface UserInfoApiInterface {
    @GET
    Call<UserInfoResponse> getUserInfo(@Header("Authorization") String authHeader, @Url String url);
}
