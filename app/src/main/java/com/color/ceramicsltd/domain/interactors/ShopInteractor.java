package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.Shop;

public interface ShopInteractor {
    interface CallBack {

        void onShopLoaded(Shop shop);

        void onShopLoadError();
    }
}
