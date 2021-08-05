package com.a7medelnoor.payoneerpaymentapplicationtask.data;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote.ApiClient;

import retrofit2.Call;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * RemoteDataSource class for our remote api response
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class RemoteDataSource implements PaymentApi{
    PaymentApi paymentApi;

    @Override
    public Call<BaseResponse> getAllMethodPayment() {
        return paymentApi.getAllMethodPayment();
    }
}