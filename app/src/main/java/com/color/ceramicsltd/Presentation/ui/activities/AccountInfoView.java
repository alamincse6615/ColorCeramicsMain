package com.color.ceramicsltd.Presentation.ui.activities;

import com.color.ceramicsltd.Models.ShippingAddress;
import com.color.ceramicsltd.Models.User;
import com.color.ceramicsltd.Network.response.ProfileInfoUpdateResponse;
import com.color.ceramicsltd.Network.response.ShippingInfoResponse;

import java.util.List;

public interface AccountInfoView {
    void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse);
    void setShippingAddresses(List<ShippingAddress> shippingAddresses);
    void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse);
    void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse);
    void setUpdatedUserInfo(User user);
}
