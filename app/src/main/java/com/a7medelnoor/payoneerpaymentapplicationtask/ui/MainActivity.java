package com.a7medelnoor.payoneerpaymentapplicationtask.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.adapter.PaymentMethodRecyclerViewAdapter;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 * <p>
 * MainActivity Model Class
 *
 * @author Ahmed Elnoor
 * @version 0.1, 05-08-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private PaymentMethodRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MainActivityViewModel viewModel;
    List<Applicable> arraylist;
    Context context;
    ProgressDialog progress;
    Toolbar toolbar;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initViews
        initViews();
        // setupActionBar
        setupActionBar();
        // setup RecyclerView
        setupRecyclerView();
        // setup Progress bar when loading data
        setupProgressBar();

        // Configure view model
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<Applicable>>() {
            @Override
            public void onChanged(List<Applicable> applicableList) {
                progress.dismiss();
                arraylist.addAll(applicableList);
                mAdapter.submitList(applicableList);
            }
        });
    }

    private void setupProgressBar() {
        // configure progress bar
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
    }

    private void setupRecyclerView() {
        // configure recyclerView
        recyclerView.hasFixedSize();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        // init array list
        arraylist = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PaymentMethodRecyclerViewAdapter(arraylist, context);
        recyclerView.setAdapter(mAdapter);
    }

    private void setupActionBar() {
        // configure action bar and title and back arrow
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        mTitle.setText(R.string.payment_methods);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        mTitle = toolbar.findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.recyclerView);
    }


    // for toolbar back arrow
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}