package com.shy.android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.lo)
    TextView lo;

    public static Integer token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AssetManager mgr = getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/but.ttf");
        login.setTypeface(tf);

        Typeface loTf = Typeface.createFromAsset(mgr, "fonts/t-text.otf");
        lo.setTypeface(loTf);
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