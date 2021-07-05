package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.User;

public interface UserInfoInteractor {
    interface CallBack {

        void onUserInfoLodaded(User user);

        void onUserInfoError();
    }
}
