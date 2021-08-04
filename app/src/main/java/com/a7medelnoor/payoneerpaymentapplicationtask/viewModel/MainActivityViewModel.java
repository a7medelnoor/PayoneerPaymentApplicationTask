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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    Repository repository;
    public MutableLiveData<List<Applicable>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private static final String TAG = "MainActivityViewModel";
    Application application;
    ArrayList<String> label = new ArrayList<>();
    ArrayList<String> linksArraylist = new ArrayList<>();
    List<Applicable> applicables;
    JSONResponse jsonResponse;
    ArrayList<Applicable> data;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
//        getPaymentMethod();
    }

//    public void getPaymentMethod() {
//        applicables = new ArrayList<>();
//        loading.setValue(true);
//        ApiClient.getPaymentService(application).getAllMethodPayment().enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                JsonArray applicable = response.body().getAsJsonObject("networks").getAsJsonArray("applicable");
//                for (int i = 0; i < applicable.size(); i++){
//                    Log.d(TAG, "onResponse: fsbssgsdgsgsrgsrgsrg"+applicable);
//                    applicables.addAll(applicables);
//                    listMutableLiveData.setValue(applicables);
//                }
//                Log.d(TAG, "onResponse: WWWWWWWWWWWWWWWWWWW"+applicable);
//                Log.d(TAG, "onResponse: MainViewModel "+response.body().getAsJsonObject("networks"));
//                JsonObject jsonObject = response.body().getAsJsonObject("networks");
//                JsonArray array = jsonObject.getAsJsonArray("applicable");
////                ArrayList<Applicable> applicable = new ArrayList<>();
//////                applicable.add(applicable.get());
////                Log.d(TAG, "onResponse: ffffffffff"+array);
////                for (int i=0; i < array.size(); i++){
////                    Applicable arrayDetails = array.get(i);
////                    Log.d(TAG, "onResponse: dssssssssssssssss"+arrayDetails.getLabel());
////                    label.add(arrayDetails.getAsString());
//////                    listMutableLiveData.setValue();
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable throwable) {
//
//            }
//        });
//
//    }
}