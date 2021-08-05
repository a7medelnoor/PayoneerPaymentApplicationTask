package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.Repository;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;

import java.util.ArrayList;
import java.util.List;
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
    List<Applicable> arraylist;
    LiveData<List<Applicable>> listLiveData;
    private final Repository repository = new Repository().getInstance();

    public MainActivityViewModel() {
        super();
        arraylist = new ArrayList<>();
        listLiveData = repository.getApplicable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<Applicable>> getListLiveData() {
        return listLiveData;
    }

}