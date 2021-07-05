package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryView {
    void onPurchaseHistoryLoaded(List<PurchaseHistory> purchaseHistoryList);
}
