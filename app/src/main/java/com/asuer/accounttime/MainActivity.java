package com.asuer.accounttime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.asuer.accounttime.retrofit.Retrofit_method;
import com.asuer.accounttime.retrofit.httpbinservice;


import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public  Retrofit retrofit;
    public httpbinservice myhttpbinservice;



    public Button bt_async;

    private Retrofit_method retrofit_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        initlistening();
        initdata();


    }




    private void initview() {
        bt_async = findViewById(R.id.bt_async);
    }

    private void initlistening() {
        bt_async.setOnClickListener(this);
    }

    private void initdata() {
        retrofit =  new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        myhttpbinservice = retrofit.create(httpbinservice.class);

        retrofit_method = new Retrofit_method(myhttpbinservice);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_async) {
            retrofit_method.postAsync();
        }
    }



}