package com.shy.android_application.util;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class NetAPI<E> implements Callback<E> {

    public  static String ip = "192.168.215.46";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://"+ip+":8010/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static API api = retrofit.create(API.class);

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        t.printStackTrace();
        Log.e("net","网络错误");
    }
}
