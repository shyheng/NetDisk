package com.shy.android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shy.android_application.util.API;
import com.shy.android_application.util.NetAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.pass)
    EditText pass;

    public static Integer token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.reg)
    public void reg(View view){
        startActivity(new Intent(MainActivity.this,RegActivity.class));
    }

    @OnClick(R.id.login)
    public void login(){
        NetAPI.api.login(new API.User(name.getText().toString(),pass.getText().toString())).enqueue(new NetAPI<API.Msg>() {
            @Override
            public void onResponse(Call<API.Msg> call, Response<API.Msg> response) {
                if (response.body().isFlg()){
                    token = response.body().token;
                    startActivity(new Intent(MainActivity.this,IndexActivity.class));
                }else {
                    Toast.makeText(MainActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}