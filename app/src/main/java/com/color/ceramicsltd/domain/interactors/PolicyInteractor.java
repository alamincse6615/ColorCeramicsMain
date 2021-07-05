package com.color.ceramicsltd.domain.interactors;

import com.color.ceramicsltd.Models.PolicyContent;

public interface PolicyInteractor {
    interface CallBack {

        void onPolicyLoaded(PolicyContent policyContent);

        void onPolicyLoadError();
    }
}
