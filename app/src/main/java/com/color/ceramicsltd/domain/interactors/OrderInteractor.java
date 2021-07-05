package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Network.response.OrderResponse;

public interface OrderInteractor {
    interface CallBack {

        void onOrderSubmitted(OrderResponse orderResponse);

        void onOrderSubmitError();
    }
}
