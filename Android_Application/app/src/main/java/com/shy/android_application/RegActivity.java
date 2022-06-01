package com.shy.android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shy.android_application.util.API;
import com.shy.android_application.util.NetAPI;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.pass1)
    EditText pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.login)
    public void login(View view){
        finish();
    }

    @OnClick(R.id.reg)
    public void reg(View view){
        if(pass.getText().toString().equals(pass1.getText().toString())){
            NetAPI.api.reg(new API.User(name.getText().toString(),pass.getText().toString())).enqueue(new NetAPI<API.Msg>() {
                @Override
                public void onResponse(Call<API.Msg> call, Response<API.Msg> response) {
                    if (response.body().isFlg()){
                        startActivity(new Intent(RegActivity.this,MainActivity.class));
                        Toast.makeText(RegActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(RegActivity.this,"密码不统一",Toast.LENGTH_SHORT).show();
        }
    }
}