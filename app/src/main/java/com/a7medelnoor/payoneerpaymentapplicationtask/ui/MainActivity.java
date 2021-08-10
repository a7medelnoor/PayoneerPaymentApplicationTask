package com.a7medelnoor.payoneerpaymentapplicationtask.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.a7medelnoor.payoneerpaymentapplicationtask.adapter.PaymentMethodRecyclerViewAdapter;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.HttpException;

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
    private static final String TAG = "MainActivity";

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
        viewModel.getApplicable();
        observeDataChange();

    }

    private void observeDataChange() {
        viewModel.getApplicableViewState().observe(this, applicableListViewState -> {
            switch (applicableListViewState.getCurrentState()) {
                case 0:
                    progress.dismiss();
                    break;
                case 1:
                    progress.dismiss();
                    loadingData(applicableListViewState.getData().getNetworks().getApplicable());
                    break;
                case -1:
                    progress.dismiss();
                    Throwable error = ApplicableListViewState.ERROR_STATE.getError();
                    if (error instanceof HttpException) {
                        HttpException httpException = (HttpException) error;
                        if (httpException.code() == 404) {
                            Log.d(TAG, "OnError: not found 404");
                            alertView("Not found");
                        } else if (httpException.code() == 500) {
                            Log.d(TAG, "OnError:Internal server error 500");
                            alertView("Internal server error");
                        } else {
                            alertView("Something went wrong");
                        }
                    }
                    break;
            }
        });
    }

    private void alertView(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    private void loadingData(List<Applicable> applicable) {
        arraylist.addAll(applicable);
        mAdapter.submitList(applicable);

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