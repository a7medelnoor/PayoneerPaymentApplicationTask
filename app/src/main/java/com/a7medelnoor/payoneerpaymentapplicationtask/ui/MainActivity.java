package com.a7medelnoor.payoneerpaymentapplicationtask.ui;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.adapter.PaymentMethodRecyclerViewAdapter;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.network.remote.ApiClient;
import com.a7medelnoor.payoneerpaymentapplicationtask.viewModel.MainActivityViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Application application;
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    private PaymentMethodRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MainActivityViewModel viewModel;
    List<JsonObject> applicables;
    ArrayList<String> label = new ArrayList<>();
    List<Applicable> labelArray  ;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // configure action bar and title and back arrow
        ActionBar actionBar = getSupportActionBar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(R.string.payment_methods);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // configure recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        applicables = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // configure view model
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass == MainActivityViewModel.class) {
                    return (T) new MainActivityViewModel(getApplication());
                }
                return null;
            }
        }).get(MainActivityViewModel.class);
        mAdapter = new PaymentMethodRecyclerViewAdapter(applicables, context);
        recyclerView.setAdapter(mAdapter);
        // access the view model
//        viewModel.listMutableLiveData.observe(this, new Observer<List<Applicable>>() {
//            @Override
//            public void onChanged(List<Applicable> applicableList) {
//                mAdapter.submitList(applicableList);
//                Log.d(TAG, "onChanged: ."+applicableList);
//            }
//        });
        getData();
    }

    private void getData() {
        ApiClient.getPaymentService(application).getAllMethodPayment().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        Log.d(TAG,"TRRRRRRRRRRRRRRRRRRRRRRr"+response.body());
                        JsonObject outerJsonObject = response.body().getAsJsonObject("networks");
                        JsonArray array = outerJsonObject.getAsJsonArray("applicable");
                        Log.d(TAG, "onResponse: .fvgfffffffffffffffff"+array);
                        for (int i =0; i< array.size(); i++){
                            JsonObject jsonObject = array.get(i).getAsJsonObject();
                            String element = jsonObject.get("label").getAsString();
                            Log.d(TAG, "onResponse eleme"+element);
                            mAdapter.submitList(Collections.singletonList(jsonObject));
                            //mAdapter.submitList();

                        }
                        //                        JsonObject jsonObject = response.body().getAsJsonObject("networks");
//                        JsonArray jsonArray = jsonObject.getAsJsonArray("applicable");
//                        Log.d(TAG, "onResponse: my data" + jsonArray);
//                        String label = jsonArray.get("");
//                        for (int i = 0; i < jsonArray.size(); i++) {
//                            mAdapter.submitList(jsonArray);
////                            Applicable applicable = labelArray.get(i);
//                            Log.d(TAG, "onBindViewHolder: my data loop" );
                    }}
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {

            }
        });
    }


    // for toolbar back arrow
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}