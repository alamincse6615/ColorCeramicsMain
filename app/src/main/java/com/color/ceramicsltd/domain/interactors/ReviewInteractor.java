package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.Review;

import java.util.List;

public interface ReviewInteractor {
    interface CallBack {

        void onReviewLodaded(List<Review> reviews);

        void onReviewError();
    }
}
