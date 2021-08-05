package com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote;

import android.content.Context;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 *
 * ApiClient Class for Retrofit builder
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class ApiClient {
    private static Context mContext;

    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
            }
        });
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClientBuilder = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).
                addInterceptor(new okhttp3.Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        if (response.code() == 404 || response.code() == 500) {
                        }
                        return response;
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClientBuilder)
                .build();
        return retrofit;
    }

    public static PaymentApi getPaymentService( ) {
        PaymentApi userPaymentServices = getRetrofit().create(PaymentApi.class);
        return userPaymentServices;
    }
}
