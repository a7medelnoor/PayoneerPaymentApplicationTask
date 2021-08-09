package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;


import androidx.lifecycle.LiveData;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.Repository;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.rxjava3.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class MainActivityViewModelTest extends TestCase {
    @Rule
//    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();

    private MainActivityViewModel viewModel;
    private Repository repository;

    @Before
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new Repository();
        viewModel = new MainActivityViewModel();
    }
    @Test
    public void getAllPaymentMethods(){
        LiveData<List<Applicable>> listLiveData = viewModel.getListLiveData();

    }
}