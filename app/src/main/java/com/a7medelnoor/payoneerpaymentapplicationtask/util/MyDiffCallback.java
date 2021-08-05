package com.a7medelnoor.payoneerpaymentapplicationtask.util;

import androidx.recyclerview.widget.DiffUtil;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;

import java.util.List;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * MyDiffCallback class for our list items
 *
 * @author Ahmed Elnoor
 * @version 0.1, 27-07-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class MyDiffCallback extends DiffUtil.Callback {

    List<Applicable> oldItem;
    List<Applicable> newItem;

    public MyDiffCallback(List<Applicable> newItem, List<Applicable> oldItem) {
        this.newItem = newItem;
        this.oldItem = oldItem;
    }

    @Override
    public int getOldListSize() {
        return oldItem.size();
    }

    @Override
    public int getNewListSize() {
        return newItem.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItem.get(oldItemPosition).equals(newItem.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItem.get(oldItemPosition) == (newItem.get(newItemPosition));
    }


}