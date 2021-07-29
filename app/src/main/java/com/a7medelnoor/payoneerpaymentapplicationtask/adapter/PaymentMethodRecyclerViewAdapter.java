package com.a7medelnoor.payoneerpaymentapplicationtask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.Constants;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.MyDiffCallback;
import com.a7medelnoor.payoneerpaymentapplicationtask.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PaymentMethodRecyclerViewAdapter extends RecyclerView.Adapter<PaymentMethodRecyclerViewAdapter.ViewHolder> {
    ArrayList<Applicable> applicableArrayList;

    public ArrayList<Applicable> getApplicableArrayList() {
        return applicableArrayList;
    }

    public void setApplicableArrayList(ArrayList<Applicable> newApplicableArrayList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(newApplicableArrayList, applicableArrayList));
        diffResult.dispatchUpdatesTo(this);
        applicableArrayList.clear();
        this.applicableArrayList.addAll(newApplicableArrayList);
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
        final Applicable applicable = applicableArrayList.get(i);
        viewHolder.paymentMethodLabel.setText(applicable.getLabel());
        Utils.loadBitmap(Constants.PAYMENT_METHOD_LOGO + applicable.getLinks(),
                viewHolder.paymentMethodLogo, viewHolder.paymentMethodLogo.getContext());
    }

    @Override
    public int getItemCount() {
        return applicableArrayList.size();
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
