package com.a7medelnoor.payoneerpaymentapplicationtask.data.network;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.JSONResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentApi {

    @GET("lists/listresult.json")
    Call<JsonObject> getAllMethodPayment();

}
