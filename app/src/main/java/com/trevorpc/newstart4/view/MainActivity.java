package com.trevorpc.newstart4.view;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.trevorpc.newstart4.R;

import com.trevorpc.newstart4.databinding.ActivityMainBinding;
import com.trevorpc.newstart4.modelview.Adapter;
import com.trevorpc.newstart4.modelview.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainBinding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.RecyclerView.setHasFixedSize(true);

        MainViewModel viewModel = new MainViewModel(this.getApplication(), this);
        viewModel.init(new MainViewModel.Callback() {
            @Override
            public void onSuccess(Adapter adapter) {
                Log.d(TAG, "onSuccess: "+adapter.getItemCount());
                mainBinding.RecyclerView.setAdapter(adapter);
            }
        });

    }
}
