package com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 *
 * BaseResponse Model Class
 *
 * @author Ahmed Elnoor
 * @version 0.1, 05-08-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class BaseResponse {
    private Networks networks;

    public Networks getNetworks() {
        return networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }

    public BaseResponse() {
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "networks=" + networks +
                '}';
    }


  
}