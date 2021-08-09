package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.Repository;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    @Mock
    private MainActivityViewModel viewModel;
    @Mock
    Repository repository;
    @Mock
    LiveData<List<Applicable>> mutableLiveData;
    @Mock
    MutableLiveData<Boolean> dataLoading = new MutableLiveData<>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MainActivityViewModel();
    }

    @Test
    public void getAllPaymentMethods() {

    }
}