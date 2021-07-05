package com.color.ceramicsltd.location;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface Api {


    public static final String BASE_URL = "https://colorceramics.com/api/v1/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())

            .build();

     //String BASE_URL = "https://colorceramics.com/api/v1/";

   /* @GET("divisions")
    Call<List<District>> getsuperHeroes();*/
}
