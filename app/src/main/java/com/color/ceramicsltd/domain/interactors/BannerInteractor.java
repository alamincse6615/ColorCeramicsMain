package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.Banner;

import java.util.List;

public interface BannerInteractor {
    interface CallBack {

        void onBannersDownloaded(List<Banner> banners);

        void onBannersDownloadError();
    }
}
