package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.FlashDealResponse;
import com.color.ceramicsltd.Network.services.FlashDealApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.FlashDealInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlashDealInteractorImpl extends AbstractInteractor {
    private FlashDealInteractor.CallBack mCallback;
    private FlashDealApiInterface apiService;

    public FlashDealInteractorImpl(Executor threadExecutor, MainThread mainThread, FlashDealInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(FlashDealApiInterface.class);
        Call<FlashDealResponse> call = apiService.getFlashDeal();

        call.enqueue(new Callback<FlashDealResponse>() {
            @Override
            public void onResponse(Call<FlashDealResponse> call, Response<FlashDealResponse> response) {
                try {
                    //Log.d("FlashDeal", response.body().getData().getTitle());
                    mCallback.onFlashDealProductDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<FlashDealResponse> call, Throwable t) {
                mCallback.onFlashDealProductDownloadError();
            }
        });

    }
}