package com.asuer.accounttime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import com.asuer.accounttime.retrofit.Retrofit_method;
import com.asuer.accounttime.retrofit.httpbinservice;


import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public  Retrofit retrofit;
    public httpbinservice myhttpbinservice;



    public Button bt_async;
    private Retrofit_method retrofit_method;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bt_async = findViewById(R.id.bt_async);
    }

    @Override
    protected void initInstance() {

    }

    @Override
    protected void initIListening() {
        bt_async.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        //初始化retrofit
        retrofit =  new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        myhttpbinservice = retrofit.create(httpbinservice.class);
        //调用方法
        retrofit_method = new Retrofit_method(myhttpbinservice);

//        // 生成数据库文件，名为 account_db
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper
//                (this, "account_db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        // 建立特定模式下的所有的 DAO 对象和数据 db 对象的映射
//        DaoMaster master = new DaoMaster(db);
//        // 管理特定模式下的所有 DAO 对象，并提供一些通用的 CRUD 持久化方法
//        DaoSession session = master.newSession();
//        // 得到指定的 StudentDao 对象
//        accountDao dao = session.getAccountDao();

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