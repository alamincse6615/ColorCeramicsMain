package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.Product;

import java.util.List;

public interface ProductInteractor {
    interface CallBack {

        void onProductDownloaded(List<Product> products);

        void onProductDownloadError();
    }
}
