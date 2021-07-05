package com.color.ceramicsltd.Presentation.ui.listeners;

import com.color.ceramicsltd.Models.CartModel;

public interface CartItemListener {
    void onCartRemove(CartModel cartModel);
    void onQuantityUpdate(int quantity, CartModel cartModel);
}
