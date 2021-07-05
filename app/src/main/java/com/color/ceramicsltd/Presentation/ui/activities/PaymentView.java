package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Network.response.CouponResponse;
import com.color.ceramicsltd.Network.response.OrderResponse;

public interface PaymentView {
    void onCouponApplied(CouponResponse couponResponse);
    void onOrderSubmitted(OrderResponse orderResponse);
}
