package com.color.ceramicsltd.Presentation.presenters;

import com.color.ceramicsltd.Models.AuctionProduct;
import com.color.ceramicsltd.Models.Banner;
import com.color.ceramicsltd.Models.Brand;
import com.color.ceramicsltd.Models.Category;
import com.color.ceramicsltd.Models.FlashDeal;
import com.color.ceramicsltd.Models.Product;
import com.color.ceramicsltd.Models.SliderImage;
import com.color.ceramicsltd.Network.response.AppSettingsResponse;
import com.color.ceramicsltd.Network.response.AuctionBidResponse;
import com.color.ceramicsltd.Presentation.ui.fragments.HomeView;
import com.color.ceramicsltd.domain.executor.Executor;
import com.color.ceramicsltd.domain.executor.MainThread;
import com.color.ceramicsltd.domain.interactors.AppSettingsInteractor;
import com.color.ceramicsltd.domain.interactors.AuctionBidInteractor;
import com.color.ceramicsltd.domain.interactors.AuctionProductInteractor;
import com.color.ceramicsltd.domain.interactors.BannerInteractor;
import com.color.ceramicsltd.domain.interactors.BestSellingInteractor;
import com.color.ceramicsltd.domain.interactors.BrandInteractor;
import com.color.ceramicsltd.domain.interactors.FeaturedProductInteractor;
import com.color.ceramicsltd.domain.interactors.FlashDealInteractor;
import com.color.ceramicsltd.domain.interactors.HomeCategoriesInteractor;
import com.color.ceramicsltd.domain.interactors.SliderInteractor;
import com.color.ceramicsltd.domain.interactors.TodaysDealInteractor;
import com.color.ceramicsltd.domain.interactors.TopCategoryInteractor;
import com.color.ceramicsltd.domain.interactors.impl.AppSettingsInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.AuctionBidInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.AuctionProductInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.BannerInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.BestSellingInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.BrandInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.FeaturedProductInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.FlashDealInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.HomeCategoriesInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.SliderInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.TodaysDealInteractorImpl;
import com.color.ceramicsltd.domain.interactors.impl.TopCategoriesInteractorImpl;
import com.google.gson.JsonObject;

import java.util.List;

public class HomePresenter extends AbstractPresenter implements AppSettingsInteractor.CallBack, SliderInteractor.CallBack, HomeCategoriesInteractor.CallBack, TodaysDealInteractor.CallBack, FlashDealInteractor.CallBack, BestSellingInteractor.CallBack, BannerInteractor.CallBack, FeaturedProductInteractor.CallBack, BrandInteractor.CallBack, TopCategoryInteractor.CallBack, AuctionProductInteractor.CallBack, AuctionBidInteractor.CallBack {
    private HomeView homeView;

    public HomePresenter(Executor executor, MainThread mainThread, HomeView homeView) {
        super(executor, mainThread);
        this.homeView = homeView;
    }

    public void getAppSettings(){
        new AppSettingsInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getSliderImages() {
        new SliderInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getHomeCategories() {
        new HomeCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTodaysDeal() {
        new TodaysDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFlashDeal() {
        new FlashDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBestSelling() {
        new BestSellingInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBanners() {
        new BannerInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFeaturedProducts() {
        new FeaturedProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTopCategories() {
        new TopCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getPopularbrands() {
        new BrandInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getAuctionProducts() {
        new AuctionProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void submitBid(JsonObject jsonObject, String token){
        new AuctionBidInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        if (homeView != null){
            homeView.onAppSettingsLoaded(appSettingsResponse);
        }
    }

    @Override
    public void onAppSettingsLoadedError() {

    }

    @Override
    public void onSliderDownloaded(List<SliderImage> sliderImages) {
        if (homeView != null) {
            homeView.setSliderImages(sliderImages);
        }
    }

    @Override
    public void onSliderDownloadError() {

    }

    @Override
    public void onHomeCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setHomeCategories(categories);
        }
    }

    @Override
    public void onHomeCategoriesDownloadError() {

    }

    @Override
    public void onTodaysDealProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setTodaysDeal(products);
        }
    }

    @Override
    public void onTodaysDealProductDownloadError() {

    }

    @Override
    public void onBestSellingProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setBestSelling(products);
        }
    }

    @Override
    public void onBestSellingProductDownloadError() {

    }

    @Override
    public void onFeaturedProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setFeaturedProducts(products);
        }
    }

    @Override
    public void onFeaturedProductDownloadError() {

    }

    @Override
    public void onTopCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setTopCategories(categories);
        }
    }

    @Override
    public void onTopCategoriesDownloadError() {

    }

    @Override
    public void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts) {
        if (homeView != null) {
            homeView.setAuctionProducts(auctionProducts);
        }
    }

    @Override
    public void onAuctionProductDownloadError() {

    }

    @Override
    public void onBannersDownloaded(List<Banner> banners) {
        if (homeView != null){
            homeView.setBanners(banners);
        }
    }

    @Override
    public void onBannersDownloadError() {

    }

    @Override
    public void onBrandsDownloaded(List<Brand> brands) {
        if (homeView != null){
            homeView.setPopularBrands(brands);
        }
    }

    @Override
    public void onBrandsDownloadError() {

    }

    @Override
    public void onBidSubmitted(AuctionBidResponse auctionBidResponse) {
        if (homeView != null){
            homeView.onAuctionBidSubmitted(auctionBidResponse);
        }
    }

    @Override
    public void onBidSubmittedError() {

    }

    @Override
    public void onFlashDealProductDownloaded(FlashDeal flashDeal) {
        if (homeView != null) {
            homeView.setFlashDeal(flashDeal);
        }
    }

    @Override
    public void onFlashDealProductDownloadError() {

    }
}