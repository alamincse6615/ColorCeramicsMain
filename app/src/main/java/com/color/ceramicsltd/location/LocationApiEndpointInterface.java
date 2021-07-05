package com.color.ceramicsltd.location;

import com.color.ceramicsltd.Models.MainDistrict;
import com.color.ceramicsltd.Models.MainDivisions;
import com.color.ceramicsltd.Models.MainThana;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiEndpointInterface {
    @GET("divisions")
    Call<MainDivisions> getAllDivisions();

    @GET("district")
    Call<MainDistrict> getAllDistricts();

    @GET("thana")
    Call<MainThana> getAllThana();
}
