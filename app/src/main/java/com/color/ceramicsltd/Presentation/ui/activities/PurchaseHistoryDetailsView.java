package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.OrderDetail;

import java.util.List;

public interface PurchaseHistoryDetailsView {
    void onOrderDetailsLoaded(List<OrderDetail> orderDetailList);
}
