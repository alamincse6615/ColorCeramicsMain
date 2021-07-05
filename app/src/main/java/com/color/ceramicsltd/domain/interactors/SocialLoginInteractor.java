package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Network.response.AuthResponse;

public interface SocialLoginInteractor {
    interface CallBack {

        void onValidLogin(AuthResponse authResponse);

        void onLoginError();
    }
}
