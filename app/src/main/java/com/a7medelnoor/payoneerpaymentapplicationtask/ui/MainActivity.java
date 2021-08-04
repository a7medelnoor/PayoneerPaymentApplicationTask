package com.a7medelnoor.payoneerpaymentapplicationtask.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.adapter.PaymentMethodRecyclerViewAdapter;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.JSONResponse;
import com.a7medelnoor.payoneerpaymentapplicationtask.viewModel.MainActivityViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private PaymentMethodRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MainActivityViewModel viewModel;
   ArrayList<Applicable> applicables;
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
        // access the view model
        viewModel.listMutableLiveData.observe(this, new Observer<JSONResponse>() {
            @Override
            public void onChanged(JSONResponse jsonResponse) {
                mAdapter = new PaymentMethodRecyclerViewAdapter(applicables, context);
                recyclerView.setAdapter(mAdapter);
            }
        });
    }


    // for toolbar back arrow
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}