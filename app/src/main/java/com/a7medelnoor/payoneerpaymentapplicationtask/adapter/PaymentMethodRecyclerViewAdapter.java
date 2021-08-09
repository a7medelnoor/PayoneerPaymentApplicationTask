package com.a7medelnoor.payoneerpaymentapplicationtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.MyDiffCallback;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.MyItemCallback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 *
 * PaymentMethod RecyclerView Adapter Class
 *
 * @author Ahmed Elnoor
 * @version 0.1, 05-08-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class PaymentMethodRecyclerViewAdapter extends ListAdapter<Applicable, PaymentMethodRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Applicable> applicableListnew;
    List<Applicable> model;

    public PaymentMethodRecyclerViewAdapter(List<Applicable> applicableList, Context context) {
        super(new MyItemCallback());
        this.context = context;
        model = applicableList;
    }

    public void setApplicableArrayList(ArrayList<Applicable> newApplicableArrayList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(newApplicableArrayList, applicableListnew));
        diffResult.dispatchUpdatesTo(this);
        applicableListnew.clear();
        this.applicableListnew.addAll(newApplicableArrayList);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_payment_method_layout, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
        viewHolder.paymentMethodLabel.setText(model.get(i).getLabel());
        Picasso.get().load(model.get(i).getLinks().getLogo()).into(viewHolder.paymentMethodLogo);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView paymentMethodLogo;
        private final TextView paymentMethodLabel;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            paymentMethodLogo = itemView.findViewById(R.id.payment_method_logo);
            paymentMethodLabel = itemView.findViewById(R.id.payment_label_title_textView);
        }
    }

}
