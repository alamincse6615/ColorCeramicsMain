package com.color.ceramicsltd.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.color.ceramicsltd.Models.ShippingAddress;
import com.color.ceramicsltd.Models.User;
import com.color.ceramicsltd.Network.response.AuthResponse;
import com.color.ceramicsltd.Network.response.ProfileInfoUpdateResponse;
import com.color.ceramicsltd.Network.response.ShippingInfoResponse;
import com.color.ceramicsltd.Presentation.presenters.AccountInfoPresenter;
import com.color.ceramicsltd.Presentation.ui.activities.AccountInfoView;
import com.color.ceramicsltd.Presentation.ui.adapters.ShippingAddressSelectAdapter;
import com.color.ceramicsltd.Presentation.ui.listeners.ShippingAddressSelectListener;
import com.color.ceramicsltd.R;
import com.color.ceramicsltd.Threading.MainThreadImpl;
import com.color.ceramicsltd.Utils.AppConfig;
import com.color.ceramicsltd.Utils.CustomToast;
import com.color.ceramicsltd.Utils.UserPrefs;
import com.color.ceramicsltd.domain.executor.impl.ThreadExecutor;
import com.google.gson.JsonObject;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

public class ShippingActivity extends BaseActivity implements AccountInfoView, ShippingAddressSelectListener {
    private AuthResponse authResponse;
    private Button payment;
    private Double total = 0.0, shipping = 0.0, tax= 0.0;
    private RecyclerView recyclerView;
    private ShippingAddress shippingAddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        total = getIntent().getDoubleExtra("total", 0.0);
        shipping = getIntent().getDoubleExtra("shipping", 0.0);
        tax = getIntent().getDoubleExtra("tax", 0.0);

        initializeActionBar();
        setTitle("Shipping Information");
        initviews();

        authResponse = new UserPrefs(this).getAuthPreferenceObjectJson("auth_response");
        if(authResponse != null && authResponse.getUser() != null){
            new AccountInfoPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getShippingAddresses(authResponse.getUser().getId(), authResponse.getAccessToken());
        }
    }

    private void initviews(){
        recyclerView = findViewById(R.id.rv_shipping_addresses);
        payment = findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shippingAddress != null){
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("total", total);
                    intent.putExtra("shipping", shipping);
                    intent.putExtra("tax", tax);

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", authResponse.getUser().getName());
                    jsonObject.addProperty("email", authResponse.getUser().getEmail());
                    jsonObject.addProperty("address", shippingAddress.getAddress());
                    jsonObject.addProperty("country", shippingAddress.getCountry());
                    jsonObject.addProperty("city", shippingAddress.getCity());
                    jsonObject.addProperty("postal_code", shippingAddress.getPostalCode());
                    jsonObject.addProperty("phone", shippingAddress.getPhone());
                    jsonObject.addProperty("checkout_type", "logged");

                    intent.putExtra("shipping_address", jsonObject.toString());

                    startActivity(intent);
                }
                else {
                    CustomToast.showToast(ShippingActivity.this, "Please choose shipping address.", R.color.colorWarning);
                }
            }
        });
    }

    @Override
    public void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse) {

    }

    @Override
    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getApplicationContext(), 10)) );
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        ShippingAddressSelectAdapter adapter = new ShippingAddressSelectAdapter(getApplicationContext(), shippingAddresses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse) {

    }

    @Override
    public void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse) {

    }

    @Override
    public void setUpdatedUserInfo(User user) {

    }

    @Override
    public void onShippingAddressItemClick(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
