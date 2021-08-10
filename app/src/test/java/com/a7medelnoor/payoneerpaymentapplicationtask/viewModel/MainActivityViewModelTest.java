package com.a7medelnoor.payoneerpaymentapplicationtask.viewModel;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Links;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Networks;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.PaymentApi;
import com.a7medelnoor.payoneerpaymentapplicationtask.ui.ApplicableListViewState;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;


@RunWith(JUnit4.class)
public class MainActivityViewModelTest {
    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    @Mock
    private MainActivityViewModel viewModel;

    @Mock
    PaymentApi api;
    @Mock
    Observer<ApplicableListViewState> observer;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;
    List<Applicable> applicables = new ArrayList<>();
    BaseResponse baseResponse = new BaseResponse();

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(h -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
        applicables.add(new Applicable("American", new Links("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png")));
        baseResponse.setNetworks(new Networks(applicables));
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new MainActivityViewModel();
        viewModel.getApplicableViewState().observeForever(observer);
    }

    @Test
    public void test_api_return_null() {
        when(api.getAllMethodPayment()).thenReturn(null);
        assertNotNull(viewModel.getApplicableViewState());
        assertTrue(viewModel.getApplicableViewState().hasObservers());
    }

    @Test
    public void test_api_return__not_null() {
        when(api.getAllMethodPayment()).thenReturn(Single.just(new BaseResponse()));
        assertNotNull(viewModel.getApplicableViewState());
        assertTrue(viewModel.getApplicableViewState().hasObservers());
    }

    @Test
    public void test_api_fetch_data_successfully() {
        viewModel = Mockito.mock(MainActivityViewModel.class);
        when(api.getAllMethodPayment()).thenReturn(Single.just(new BaseResponse()));
        viewModel.getApplicable();
        verify(viewModel).getApplicable();
    }

    @Test
    public void test_api_fetch_data_error() {
        viewModel = Mockito.mock(MainActivityViewModel.class);
        when(api.getAllMethodPayment()).thenReturn(Single.error(new Throwable("Api error")));
        viewModel.getApplicable();
        verify(viewModel, Mockito.times(1)).getApplicable();
    }

    @Test
    public void test_api_fetch_data_once() {
        viewModel = Mockito.mock(MainActivityViewModel.class);
        viewModel.getApplicable();
        verify(viewModel, Mockito.times(1)).getApplicable();
    }

    @Test
    public void test_that_the_return_is_list_of_applicable() {
        viewModel = Mockito.mock(MainActivityViewModel.class);
        ApplicableListViewState.SUCCESS_STATE.setData(baseResponse);
        ApplicableListViewState viewState = ApplicableListViewState.SUCCESS_STATE;

        when(viewModel.getApplicableViewState()).thenReturn(new MutableLiveData(viewState));
        viewModel.getApplicable();
        List<Applicable> app = viewState.getData().getNetworks().getApplicable();
        Truth.assertThat(app).isInstanceOf(List.class);
    }

    @Test
    public void test_that_the_get_applicable_return_list_with_size_equals_one() {
        viewModel = Mockito.mock(MainActivityViewModel.class);
        ApplicableListViewState.SUCCESS_STATE.setData(baseResponse);
        ApplicableListViewState viewState = ApplicableListViewState.SUCCESS_STATE;
        when(viewModel.getApplicableViewState()).thenReturn(new MutableLiveData<>(viewState));
        List<Applicable> app = viewState.getData().getNetworks().getApplicable();
        Truth.assertThat(app.size()).isEqualTo(1);
    }

    @Test
    public void test_that_the_applicable_return_with_size_equals_to_what_expected() {

        viewModel = Mockito.mock(MainActivityViewModel.class);
        ApplicableListViewState.SUCCESS_STATE.setData(baseResponse);
        ApplicableListViewState viewState = ApplicableListViewState.SUCCESS_STATE;
        when(viewModel.getApplicableViewState()).thenReturn(new MutableLiveData<>(viewState));
        List<Applicable> app = viewState.getData().getNetworks().getApplicable();
        Truth.assertThat(app.get(0).getLabel()).isEqualTo("American");


    }


}