package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.UserBidResponse;
import com.color.ceramicsltd.Network.services.UserBidApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.UserBidInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBidInteractorImpl extends AbstractInteractor {
    private UserBidInteractor.CallBack mCallback;
    private UserBidApiInterface apiService;
    private int user_id;
    private String token;

    public UserBidInteractorImpl(Executor threadExecutor, MainThread mainThread, UserBidInteractor.CallBack callBack, int id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = id;
        this.token = "Bearer "+token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(UserBidApiInterface.class);
        Call<UserBidResponse> call = apiService.getUserBids(token,"auctions/bids/"+user_id);

        call.enqueue(new Callback<UserBidResponse>() {
            @Override
            public void onResponse(Call<UserBidResponse> call, Response<UserBidResponse> response) {
                try {
                    mCallback.onUserBidLodaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserBidResponse> call, Throwable t) {
                mCallback.onUserBidError();
            }
        });

    }
}
