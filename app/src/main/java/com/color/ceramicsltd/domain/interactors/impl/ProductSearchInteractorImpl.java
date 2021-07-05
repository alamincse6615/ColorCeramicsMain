package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.ProductSearchResponse;
import com.color.ceramicsltd.Network.services.ProductSearchApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.ProductSearchInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSearchInteractorImpl extends AbstractInteractor {
    private ProductSearchInteractor.CallBack mCallback;
    private ProductSearchApiInterface apiService;
    private String url;

    public ProductSearchInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductSearchInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductSearchApiInterface.class);
        Call<ProductSearchResponse> call = apiService.getSearchedProducts(url);

        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                try {
                    mCallback.onProductSearched(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
                mCallback.onProductSearchedError();
            }
        });

    }
}
