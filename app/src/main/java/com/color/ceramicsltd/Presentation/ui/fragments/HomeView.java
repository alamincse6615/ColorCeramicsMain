package com.color.ceramicsltd.Presentation.ui.fragments;

import com.color.ceramicsltd.Models.AuctionProduct;
import com.color.ceramicsltd.Models.Banner;
import com.color.ceramicsltd.Models.Brand;
import com.color.ceramicsltd.Models.Category;
import com.color.ceramicsltd.Models.FlashDeal;
import com.color.ceramicsltd.Models.Product;
import com.color.ceramicsltd.Models.SliderImage;
import com.color.ceramicsltd.Network.response.AppSettingsResponse;
import com.color.ceramicsltd.Network.response.AuctionBidResponse;

import java.util.List;

public interface HomeView {
    void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

    void setSliderImages(List<SliderImage> sliderImages);

    void setHomeCategories(List<Category> categories);

    void setTodaysDeal(List<Product> products);

    void setFlashDeal(FlashDeal flashDeal);

    void setBanners(List<Banner> banners);

    void setBestSelling(List<Product> products);

    void setFeaturedProducts(List<Product> products);

    void setTopCategories(List<Category> categories);

    void setPopularBrands(List<Brand> brands);

    void setAuctionProducts(List<AuctionProduct> auctionProducts);

    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}