package com.color.ceramicsltd.location;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api myApi;
    LocationApiEndpointInterface allDivisions;
    LocationApiEndpointInterface allDistrict;
    LocationApiEndpointInterface allThana;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        allDivisions = retrofit.create(LocationApiEndpointInterface.class);
        allDistrict = retrofit.create(LocationApiEndpointInterface.class);
        allThana = retrofit.create(LocationApiEndpointInterface.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public LocationApiEndpointInterface getAllDivision(){
        return allDivisions;
    }

    public LocationApiEndpointInterface getAllDistrict(){
        return allDistrict;
    }
    public LocationApiEndpointInterface getAllThana(){
        return allThana;
    }
}
