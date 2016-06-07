package com.typicode.jsonplaceholder.rest;

/**
 * Created by REEZA on 6/7/2016.
 */

import com.typicode.jsonplaceholder.models.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("posts")
    Call<Model> getUserId();
}
