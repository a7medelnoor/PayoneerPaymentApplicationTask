package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.Repository;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.JSONResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    Repository repository;
    public MutableLiveData<JSONResponse> listMutableLiveData = new MutableLiveData<JSONResponse>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private static final String TAG = "MainActivityViewModel";
    Application application;
    ArrayList<String> label = new ArrayList<>();
    ArrayList<String> Links = new ArrayList<>();
    List<Applicable> applicables;
    JSONResponse jsonResponse;
    ArrayList<Applicable> data;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        getPaymentMethod();
    }

    public void getPaymentMethod() {
        applicables = new ArrayList<>();
        loading.setValue(true);
        ApiClient.getPaymentService(application).getAllMethodPayment().enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    JSONResponse jsonResponses = response.body();
                    Log.d(TAG, "onResponse: response boy " + jsonResponses);
//                        data = new ArrayList<>(Arrays.asList(jsonResponses.getApplicables());
                    Log.d(TAG, "onResponse: " + response.body());
//                        JSONResponse jsonResponse = response.body();
                    listMutableLiveData.setValue(jsonResponses);
                } else if (response.code() == 404) {
                    Log.d(TAG, "APPError item not found");
                } else if (response.code() == 500) {
                    Log.d(TAG, "APPError something occur");

                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable throwable) {

            }
        });

    }
}