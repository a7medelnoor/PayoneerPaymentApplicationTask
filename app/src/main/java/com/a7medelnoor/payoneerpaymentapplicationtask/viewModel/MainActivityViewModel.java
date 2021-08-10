package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote.ApiClient;
import com.a7medelnoor.payoneerpaymentapplicationtask.ui.ApplicableListViewState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
 * MainActivityViewModel class for making request and observe our list to ui via liveData
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable disposable;
    private final MutableLiveData<ApplicableListViewState> applicableViewState = new MutableLiveData<>();
    private PaymentApi api;
    private List<Applicable> arraylist = new ArrayList<>();
    private static final String TAG = "MainActivityViewModel";
    private Context context;

    public MutableLiveData<ApplicableListViewState> getApplicableViewState() {
        return applicableViewState;
    }


    public MainActivityViewModel() {
        super();
        disposable = new CompositeDisposable();
    }

    public void getApplicable() {
        disposable.add(
                ApiClient.getPaymentService().getAllMethodPayment()
                        .doOnEvent((baseResponse, throwable) -> onLoading())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccess, this::onError));

    }
    public List<Applicable> getApplicableList() {

        ApiClient.getPaymentService().getAllMethodPaymentForTesting().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        arraylist.addAll(response.body().getNetworks().getApplicable());
                    }
                } else if (response.code() == 404) {
                    Log.d(TAG,"response body 404"+response.body());
                    Toast.makeText(context.getApplicationContext(), R.string.paymentMethod_message, Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Log.d(TAG,"response body 500"+response.body());

                    Toast.makeText(context.getApplicationContext(), R.string.serverError, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
        return arraylist;
    }

    private void onSuccess(BaseResponse baseResponse) {
        ApplicableListViewState.SUCCESS_STATE.setData(baseResponse);
        applicableViewState.postValue(ApplicableListViewState.SUCCESS_STATE);
    }

    private void onError(Throwable error) {
        ApplicableListViewState.ERROR_STATE.setError(error);
        applicableViewState.postValue(ApplicableListViewState.ERROR_STATE);
    }

    private void onLoading() {
        applicableViewState.postValue(ApplicableListViewState.LOADING_STATE);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }


}