package com.a7medelnoor.payoneerpaymentapplicationtask.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;

import org.jetbrains.annotations.NotNull;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * MyItemCallback class for our list items
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class MyItemCallback  extends DiffUtil.ItemCallback {
    Applicable applicable;
    @Override
    public boolean areItemsTheSame(@NonNull @NotNull Object o, @NonNull @NotNull Object t1) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull @NotNull Object o, @NonNull @NotNull Object t1) {
        return false;
    }
}
