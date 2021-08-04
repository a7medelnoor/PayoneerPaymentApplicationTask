package com.a7medelnoor.payoneerpaymentapplicationtask.adapter;

import android.content.Context;
import android.util.Log;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRecyclerViewAdapter extends ListAdapter<JsonObject, PaymentMethodRecyclerViewAdapter.ViewHolder> {
    ArrayList<JSONArray> applicableArrayList;
    Context context;
    ArrayList<Applicable> applicableListnew;
    List<JsonObject> model;

    private static final String TAG = "PaymentMethodRecyclerVi";

    public PaymentMethodRecyclerViewAdapter(List<JsonObject> applicableList, Context context) {
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
        viewHolder.paymentMethodLabel.setText(model.get(i).getAsJsonObject("label").toString());
        Log.d(TAG, "onBindViewHolder: "+model.get(i).getAsJsonObject("label"));
//        Applicable applicable = applicableListnew.get(i);
//        viewHolder.paymentMethodLabel.setText(applicable.getLabel());
//        Log.d(TAG, "onBindViewHolder: "+applicable.getLinks());
//        Log.d(TAG, "onBindViewHolder:ddddddddddddd "+applicable.getLabel());
//        JSONArray jsonElements = applicableArrayList.get(i);
//        Log.d(TAG, "onBindViewHolder: sssssssssss"+jsonElements);
//        for (int is = 0; is < jsonElements.length(); is++) {
//            try {
//                JSONObject applicableDetails = jsonElements.getJSONObject(is);
//                Log.d(TAG, "onBindViewHolder: neww"+applicableDetails.get("label"));
//                viewHolder.paymentMethodLabel.setText(applicableDetails.get("label").toString());
//                JSONObject jsonObject2 = applicableDetails.getJSONObject("links");
//                Picasso.get().load(jsonObject2.get("logo").toString()).into(viewHolder.paymentMethodLogo);
//                JSONObject jsonElement = jsonObject2.getJSONObject("logo");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }

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
