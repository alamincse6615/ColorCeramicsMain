package com.color.ceramicsltd.Network.services;

import com.color.ceramicsltd.Network.response.UserBidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface UserBidApiInterface {
    @GET
    Call<UserBidResponse> getUserBids(@Header("Authorization") String authHeader, @Url String url);
}
