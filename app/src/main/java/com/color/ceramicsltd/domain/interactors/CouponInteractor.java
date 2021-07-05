package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Network.response.CouponResponse;

public interface CouponInteractor {
    interface CallBack {

        void onCouponApplied(CouponResponse couponResponse);

        void onCouponAppliedError();
    }
}
