package com.shy.android_application.util;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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
    Call<String> file( @Part("token") Integer token, @Part MultipartBody.Part file);

    class NetFile{
        public Integer id;
        public String imgGroup;
        public String imgRemtoe;
        public String size;
        public String fileName;
        public String type;
        public String date;
        public Integer uid;
    }
    @GET("/sel")
    Call<List<NetFile>> netFile(@Query("id") Integer id);


    @GET("/dow")
    Call<ResponseBody> download(@Query("id") Integer id);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);

    @POST("/del")
    Call<String> del(@Body NetFile netFile);
}
