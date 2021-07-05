package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.CartModel;

import java.util.List;

public interface CartInteractor {
    interface CallBack {

        void onCartLodaded(List<CartModel> cartModel);

        void onCartError();
    }
}
