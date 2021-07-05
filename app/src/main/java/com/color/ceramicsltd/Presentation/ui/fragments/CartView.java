package com.color.ceramicsltd.Presentation.ui.fragments;

import com.color.ceramicsltd.Models.CartModel;
import com.color.ceramicsltd.Network.response.CartQuantityUpdateResponse;
import com.color.ceramicsltd.Network.response.RemoveCartResponse;

import java.util.List;

public interface CartView {
    void setCartItems(List<CartModel> cartItems);
    void showRemoveCartMessage(RemoveCartResponse removeCartResponse);
    void showCartQuantityUpdateMessage(CartQuantityUpdateResponse cartQuantityUpdateResponse);
}
