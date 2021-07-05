package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.Wallet;

import java.util.List;

public interface WalletView {
    void setWalletBalance(Double balance);
    void setWalletHistory(List<Wallet> walletList);
}
