package com.a7medelnoor.payoneerpaymentapplicationtask.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote.ApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * Repository class for making request and observe our list to via liveData
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class Repository {
    public static final Repository repository = new Repository();
    private final PaymentApi paymentApi;
    private final MutableLiveData<List<Applicable>> listMutableLiveData = new MutableLiveData<>();
    private final List<Applicable> arraylist;
    private Context context;
    private static final String TAG = "Repository";

    public static Repository getInstance() {
        return repository;
    }

    public Repository() {
        paymentApi = ApiClient.getPaymentService();
        arraylist = new ArrayList<>();
    }

//    public LiveData<List<Applicable>> getApplicable() {
//        paymentApi.getAllMethodPayment().enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body() != null) {
//                        arraylist.addAll(response.body().networks.applicable);
//                        listMutableLiveData.setValue(arraylist);
//                    }
//                } else if (response.code() == 404) {
//                    Log.d(TAG,"response body 404"+response.body());
//                    Toast.makeText(context, R.string.paymentMethod_message, Toast.LENGTH_SHORT).show();
//                } else if (response.code() == 500) {
//                    Log.d(TAG,"response body 500"+response.body());
//
//                    Toast.makeText(context, R.string.serverError, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
//
//            }
//        });
//        return listMutableLiveData;
//    }
    public List<Applicable> getApplicable() {
        paymentApi.getAllMethodPayment().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        arraylist.addAll(response.body().networks.applicable);
                    }
                } else if (response.code() == 404) {
                    Log.d(TAG,"response body 404"+response.body());
                    Toast.makeText(context, R.string.paymentMethod_message, Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Log.d(TAG,"response body 500"+response.body());

                    Toast.makeText(context, R.string.serverError, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
            }
        });
        return arraylist;
    }
}
