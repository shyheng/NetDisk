package com.shy.android_application.util;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetAPI<E> implements Callback<E> {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.20.37.191:8010/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static API api = retrofit.create(API.class);

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        t.printStackTrace();
        Log.e("net","网络错误");
    }
}
