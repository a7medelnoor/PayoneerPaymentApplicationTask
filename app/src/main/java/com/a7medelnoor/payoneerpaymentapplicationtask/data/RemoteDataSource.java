package com.a7medelnoor.payoneerpaymentapplicationtask.data;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.JSONResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;

import retrofit2.Call;

public class RemoteDataSource implements PaymentApi {
    PaymentApi paymentApi;

    @Override
    public Call<JSONResponse> getAllMethodPayment() {
        return paymentApi.getAllMethodPayment();
    }
}