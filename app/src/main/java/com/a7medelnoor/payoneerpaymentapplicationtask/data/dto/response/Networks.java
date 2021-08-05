package com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response;

import java.util.List;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 *
 * Networks Model Class
 *
 * @author Ahmed Elnoor
 * @version 0.1, 05-08-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class Networks{
    @Override
    public String toString() {
        return "Networks{" +
                "applicable=" + applicable +
                '}';
    }

    public List<Applicable> applicable;
}