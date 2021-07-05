package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Network.response.AuthResponse;

public interface LoginInteractor {
    interface CallBack {

        void onValidLogin(AuthResponse authResponse);

        void onLoginError();
    }
}
