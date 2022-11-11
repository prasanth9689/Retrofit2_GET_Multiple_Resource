package com.skyblue.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

interface APIInterface {
    @GET("/web/unknown.json")
    Call<MultipleResource> doGetListResources();
}