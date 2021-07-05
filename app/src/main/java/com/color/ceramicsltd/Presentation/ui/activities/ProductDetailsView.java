package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.Product;
import com.color.ceramicsltd.Models.ProductDetails;
import com.color.ceramicsltd.Network.response.AddToCartResponse;
import com.color.ceramicsltd.Network.response.AddToWishlistResponse;
import com.color.ceramicsltd.Network.response.CheckWishlistResponse;
import com.color.ceramicsltd.Network.response.RemoveWishlistResponse;

import java.util.List;

public interface ProductDetailsView {
    void setProductDetails(ProductDetails productDetails);
    void setRelatedProducts(List<Product> relatedProducts);
    void setTopSellingProducts(List<Product> topSellingProducts);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
    void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage);
    void onCheckWishlist(CheckWishlistResponse checkWishlistResponse);
    void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse);
}
