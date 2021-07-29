package com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote;

import android.content.Context;
import android.util.Log;

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

public class ApiClient {
    private static Context mContext;

    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.e("OKHTTP LOG", s);
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

    public static PaymentApi getPaymentService(Context context) {
        mContext = context;
        PaymentApi userPaymentServices = getRetrofit().create(PaymentApi.class);
        return userPaymentServices;
    }
}
