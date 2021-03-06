package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.ShippingResponse;
import com.color.ceramicsltd.Network.services.ShippingInfoApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.ShippingInfoInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingInfoInteractorImpl extends AbstractInteractor {
    private ShippingInfoInteractor.CallBack mCallback;
    private ShippingInfoApiInterface apiService;
    private int user_id;
    private String auth_token;

    public ShippingInfoInteractorImpl(Executor threadExecutor, MainThread mainThread, ShippingInfoInteractor.CallBack callBack, int user_id, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = user_id;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ShippingInfoApiInterface.class);

        Call<ShippingResponse> call = apiService.getShippingAddress(auth_token, user_id);

        call.enqueue(new Callback<ShippingResponse>() {
            @Override
            public void onResponse(Call<ShippingResponse> call, Response<ShippingResponse> response) {
                try {
                    mCallback.onShippingInfoRetrived(response.body().getData());
                } catch (Exception e) {
                    Log.e("Shipping Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ShippingResponse> call, Throwable t) {
                Log.d("Shipping Test", t.getMessage());
                mCallback.onShippingInfoRetrivedError();
            }
        });

    }
}
