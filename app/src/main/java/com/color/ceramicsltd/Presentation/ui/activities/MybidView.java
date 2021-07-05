package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.UserBid;
import com.color.ceramicsltd.Network.response.AuctionBidResponse;

import java.util.List;

public interface MybidView {
    void setUserBids(List<UserBid> userBids);
    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}
