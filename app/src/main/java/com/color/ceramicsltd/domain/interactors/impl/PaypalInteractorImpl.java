package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.OrderResponse;
import com.color.ceramicsltd.Network.services.PayPalApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.PaypalInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaypalInteractorImpl extends AbstractInteractor {
    private PaypalInteractor.CallBack mCallback;
    private PayPalApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;

    public PaypalInteractorImpl(Executor threadExecutor, MainThread mainThread, PaypalInteractor.CallBack callBack, String auth_token, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.auth_token = "Bearer "+auth_token;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(PayPalApiInterface.class);

        Call<OrderResponse> call = apiService.sendPlaceOrderRequest(auth_token, jsonObject);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onPayaplOrderSubmitted(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onPayaplOrderSubmitError();
            }
        });

    }
}
