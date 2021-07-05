package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Network.response.AddToCartResponse;
import com.color.ceramicsltd.Network.response.VariantResponse;

public interface BuyingOptionView {
    void setVariantprice(VariantResponse variantResponse);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
}
