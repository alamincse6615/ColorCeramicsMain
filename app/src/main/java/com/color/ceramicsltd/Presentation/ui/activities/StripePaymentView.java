package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Network.response.StripeClientSecretResponse;

public interface StripePaymentView {
    void onClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);
}
