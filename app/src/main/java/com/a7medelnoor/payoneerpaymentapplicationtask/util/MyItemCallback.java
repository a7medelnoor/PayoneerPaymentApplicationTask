package com.a7medelnoor.payoneerpaymentapplicationtask.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;

import org.jetbrains.annotations.NotNull;

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
