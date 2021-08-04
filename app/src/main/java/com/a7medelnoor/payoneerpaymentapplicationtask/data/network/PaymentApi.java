package com.a7medelnoor.payoneerpaymentapplicationtask.data.network;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentApi {
    @GET("/optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<JSONResponse> getAllMethodPayment();

}
