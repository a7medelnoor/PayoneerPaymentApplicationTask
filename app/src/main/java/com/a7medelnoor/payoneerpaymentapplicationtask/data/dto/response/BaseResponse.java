package com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response;

public class BaseResponse {
    public Networks networks;

    public Networks getNetworks() {
        return networks;
    }

    public BaseResponse(Networks networks) {
        this.networks = networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }
}
