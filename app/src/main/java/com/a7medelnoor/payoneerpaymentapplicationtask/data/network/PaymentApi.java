package com.a7medelnoor.payoneerpaymentapplicationtask.data.network;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * PaymentApi Interface for our api response
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public interface PaymentApi {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Single<BaseResponse> getAllMethodPayment();
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<BaseResponse> getAllMethodPaymentForTesting();

}
