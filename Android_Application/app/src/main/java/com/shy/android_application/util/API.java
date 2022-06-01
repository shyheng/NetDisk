package com.shy.android_application.util;

import okhttp3.MultipartBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    class Msg{
        String msg;
        boolean flg;
        public Integer token;
        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isFlg() {
            return flg;
        }

        public void setFlg(boolean flg) {
            this.flg = flg;
        }
    }

    class User{

        public String name;
        public String pass;

        public User(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }
    }
    @POST("/login")
    Call<Msg> login(@Body User user);

    @POST("/reg")
    Call<Msg> reg(@Body User user);

    @Multipart
    @POST("/file")
    Call<String> file(@Part MultipartBody.Part file,@Body Integer token);

}
