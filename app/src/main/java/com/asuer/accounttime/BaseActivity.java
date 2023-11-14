package com.asuer.accounttime;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    protected abstract void initView();
    protected abstract void initInstance();

    protected abstract void initIListening();


    protected abstract void loadData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initInstance();
        initIListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }
}
