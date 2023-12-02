package com.ant.smartclubfoot.api;

import com.ant.smartclubfoot.model.LoginObject;
import com.ant.smartclubfoot.model.ReadingResponse;
import com.ant.smartclubfoot.model.ReadingsNew;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiUrls {

    @GET("graph/{device_id}/{date}")
    Call<ReadingsNew> getReading(@Path(value = "device_id") String userId, @Path(value ="date") String date);

    @Headers("Content-Type: application/json")
    @POST("readings/{deviceId}")
    Call<JSONObject> postReading(@Path("deviceId") String deviceId, @Body JsonArray jsonArray);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("users/login")
    Call<JSONObject> login(@Body LoginObject loginObject);
}
