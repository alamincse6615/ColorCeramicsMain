package com.color.ceramicsltd.domain.interactors.impl;

import android.util.Log;

import com.color.ceramicsltd.Network.ApiClient;
import com.color.ceramicsltd.Network.response.CategoryResponse;
import com.color.ceramicsltd.Network.services.AllCategoryApiInterface;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.AllCategoryInteractor;
import com.color.ceramicsltd.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoriesInteractorImpl extends AbstractInteractor {
    private AllCategoryInteractor.CallBack mCallback;
    private AllCategoryApiInterface apiService;

    public AllCategoriesInteractorImpl(Executor threadExecutor, MainThread mainThread, AllCategoryInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(AllCategoryApiInterface.class);
        Call<CategoryResponse> call = apiService.getAllCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                try {
                    mCallback.onAllCategoriesDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                mCallback.onAllCategoriesDownloadError();
            }
        });

    }
}
