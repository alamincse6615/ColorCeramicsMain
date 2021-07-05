package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Network.response.LogoutResponse;

public interface LogoutInteractor {
    interface CallBack {

        void onLoggedOut(LogoutResponse logoutResponse);

        void onLoggedOutError();
    }
}
