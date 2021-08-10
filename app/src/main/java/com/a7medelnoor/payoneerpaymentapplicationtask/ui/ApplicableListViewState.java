package com.a7medelnoor.payoneerpaymentapplicationtask.ui;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.BaseResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.BaseViewState;

public class ApplicableListViewState extends BaseViewState<BaseResponse> {
    private ApplicableListViewState(BaseResponse data, int currentState, Throwable error) {
        this.data = data;
        this.error = error;
        this.currentState = currentState;
    }

    public static ApplicableListViewState ERROR_STATE = new ApplicableListViewState(null, State.FAILED.value, new Throwable());
    public static ApplicableListViewState LOADING_STATE = new ApplicableListViewState(null, State.LOADING.value, null);
    public static ApplicableListViewState SUCCESS_STATE = new ApplicableListViewState(new BaseResponse(), State.SUCCESS.value, null);

}