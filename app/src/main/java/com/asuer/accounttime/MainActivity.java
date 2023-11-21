package com.asuer.accounttime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


import com.asuer.accounttime.dialog.AccountInformationDialog;
import com.asuer.accounttime.greendao.Account;
import com.asuer.accounttime.greendao.accountManager;
import com.asuer.accounttime.retrofit.Retrofit_method;
import com.asuer.accounttime.retrofit.httpbinservice;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public  Retrofit retrofit;
    public httpbinservice myhttpbinservice;



    public Button bt_async, bt_add_account, bt_load_all_account;
    private Retrofit_method retrofit_method;

    private accountManager mAccountmanager;
    private AccountInformationDialog accountInformationDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bt_async = findViewById(R.id.bt_async);
        bt_add_account = findViewById(R.id.bt_add_account);
        bt_load_all_account = findViewById(R.id.bt_load_all_account);
    }

    @Override
    protected void initInstance() {

    }

    @Override
    protected void initIListening() {
        bt_async.setOnClickListener(this);
        bt_add_account.setOnClickListener(this);
        bt_load_all_account.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        //初始化retrofit
        retrofit =  new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        myhttpbinservice = retrofit.create(httpbinservice.class);
        //调用方法
        retrofit_method = new Retrofit_method(myhttpbinservice);

        //初始化GreenDao
        mAccountmanager = accountManager.getmInstance(MainActivity.this);


        //添加消费信息弹窗实例化
        accountInformationDialog = new AccountInformationDialog(MainActivity.this);


    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bt_async:
                retrofit_method.postAsync();
                break;

            case R.id.bt_add_account:
                accountInformationDialog.ShowAddAccountDialog();
                break;

            case R.id.bt_load_all_account:
                List<Account> AccountList = mAccountmanager.loadAll();
                for (int i = 0; i < AccountList.size(); i++) {
                    Log.e("TAG", AccountList.get(i).toString());
                }
                break;

        }
    }


}