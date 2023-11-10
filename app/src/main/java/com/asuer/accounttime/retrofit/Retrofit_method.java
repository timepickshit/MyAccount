package com.asuer.accounttime.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Retrofit_method {

    private httpbinservice httpbinservice;
    public Retrofit_method(httpbinservice httpbinservice) {
        this.httpbinservice = httpbinservice;
    }
    public void postAsync() {
        retrofit2.Call<ResponseBody> call = httpbinservice.post("lance", "152522");
        call.enqueue(new retrofit2.Callback<ResponseBody>(){

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("TAG", "postAsync" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("TAG", "请求失败");
            }
        });
    }
}
