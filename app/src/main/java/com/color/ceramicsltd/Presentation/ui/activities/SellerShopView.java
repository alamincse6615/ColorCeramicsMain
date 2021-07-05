package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.Product;
import com.color.ceramicsltd.Models.Shop;

import java.util.List;

public interface SellerShopView {
    void onShopDetailsLoaded(Shop shop);
    void setFeaturedProducts(List<Product> products);
    void setTopSellingProducts(List<Product> products);
    void setNewProducts(List<Product> products);
}
