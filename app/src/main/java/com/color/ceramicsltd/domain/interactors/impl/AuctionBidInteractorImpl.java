package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.AuctionBidResponse;
import com.color.ceramicsltd.Network.services.AuctionBidApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.AuctionBidInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionBidInteractorImpl extends AbstractInteractor {
    private AuctionBidInteractor.CallBack mCallback;
    private AuctionBidApiInterface apiService;
    private String auth_token;
    private JsonObject jsonObject;

    public AuctionBidInteractorImpl(Executor threadExecutor, MainThread mainThread, AuctionBidInteractor.CallBack callBack, JsonObject jsonObject, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.jsonObject = jsonObject;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(AuctionBidApiInterface.class);

        Call<AuctionBidResponse> call = apiService.submitAuctionBid(auth_token, jsonObject);

        call.enqueue(new Callback<AuctionBidResponse>() {
            @Override
            public void onResponse(Call<AuctionBidResponse> call, Response<AuctionBidResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onBidSubmitted(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AuctionBidResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onBidSubmittedError();
            }
        });

    }
}
