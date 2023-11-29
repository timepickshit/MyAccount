package com.asuer.accounttime;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


import com.asuer.accounttime.dialog.AccountInformationDialog;
import com.asuer.accounttime.greendao.Account;
import com.asuer.accounttime.greendao.AccountManager;
import com.asuer.accounttime.retrofit.Retrofit_method;
import com.asuer.accounttime.retrofit.httpbinservice;


import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public  Retrofit retrofit;
    public httpbinservice myhttpbinservice;



    private ImageView iv_add_account;
    public Button bt_async, bt_load_all_account;

    public ListView lv_account;

    private Retrofit_method retrofit_method;

    private AccountManager mAccountmanager;
    private AccountInformationDialog accountInformationDialog;
    private Homepage_Adapter mHomepage_Adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bt_async = findViewById(R.id.bt_async);
        bt_load_all_account = findViewById(R.id.bt_load_all_account);
        iv_add_account = findViewById(R.id.iv_add_account);
        lv_account = findViewById(R.id.lv_account);
    }

    @Override
    protected void initInstance() {

    }

    @Override
    protected void initIListening() {
        bt_async.setOnClickListener(this);
        bt_load_all_account.setOnClickListener(this);
        iv_add_account.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

        //初始化retrofit
        retrofit =  new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        myhttpbinservice = retrofit.create(httpbinservice.class);
        //调用方法
        retrofit_method = new Retrofit_method(myhttpbinservice);

        //初始化GreenDao
        mAccountmanager = AccountManager.getmInstance(MainActivity.this);


        //添加消费信息弹窗实例化
        accountInformationDialog = new AccountInformationDialog(MainActivity.this);

        List<Account> AccountList = mAccountmanager.loadAll();
        for (int i = 0; i < AccountList.size(); i++) {
            Log.e("TAG", AccountList.get(i).toString());
        }
        mHomepage_Adapter = new Homepage_Adapter(MainActivity.this, AccountList);
        lv_account.setAdapter(mHomepage_Adapter);

    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bt_async:
                retrofit_method.postAsync();
                break;

            case R.id.iv_add_account:
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