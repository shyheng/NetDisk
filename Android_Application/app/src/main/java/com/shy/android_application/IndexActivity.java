package com.shy.android_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shy.android_application.util.API;
import com.shy.android_application.util.Adapter;
import com.shy.android_application.util.NetAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.rv_sel)
    RecyclerView re_sel;

    @BindView(R.id.file)
    Button file;

    public static AssetManager mg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        AssetManager mgr = getAssets();
        mg = mgr;
        re_sel.setLayoutManager(new GridLayoutManager(this,1));
        NetAPI.api.netFile(MainActivity.token).enqueue(new NetAPI<List<API.NetFile>>() {
            @Override
            public void onResponse(Call<List<API.NetFile>> call, Response<List<API.NetFile>> response) {
                List<API.NetFile> body = response.body();
                Adapter adapter = new Adapter(body);
                re_sel.setAdapter(adapter);
                adapter.setOnRecyclerItemClickDow(new Adapter.OnRecyclerItemClickDow() {
                    @Override
                    public void OnRecyclerItemClickDow(int position) {
                        NetAPI.api.download("http://124.221.224.125/"+body.get(position).imgGroup+"/"+body.get(position).imgRemtoe).enqueue(new NetAPI<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                try {

                                    assert response.body() != null;
                                    InputStream inputStream = response.body().byteStream();
                                    FileOutputStream fos = new FileOutputStream("/storage/emulated/0/Download/"+body.get(position).fileName);
                                    int len;
                                    byte[] bytes = new byte[1024];
                                    while ((len = inputStream.read(bytes)) != -1) {
                                        fos.write(bytes,0,len);
                                    }
                                    Toast.makeText(getApplicationContext(),"以下载到您的下载文件下",Toast.LENGTH_SHORT).show();
                                    fos.close();
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });

                adapter.setOnRecyclerItemClickDel(new Adapter.OnRecyclerItemClickDel() {
                    @Override
                    public void OnRecyclerItemClickDel(int position) {
                        NetAPI.api.del(body.get(position)).enqueue(new NetAPI<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_SHORT).show();
                                NetAPI.api.netFile(MainActivity.token).enqueue(new NetAPI<List<API.NetFile>>() {
                                    @Override
                                    public void onResponse(Call<List<API.NetFile>> call, Response<List<API.NetFile>> response) {
                                        re_sel.setAdapter(new Adapter(response.body()));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });



    }

    @OnClick(R.id.file)
    public void f() {
        if (ContextCompat.checkSelfPermission(IndexActivity.this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IndexActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                openAlbum();
            else Toast.makeText(IndexActivity.this, "你拒绝了", Toast.LENGTH_SHORT).show();
        }
    }
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK && data != null) {
                if (Build.VERSION.SDK_INT >= 19)
                    handImage(data);
                else handImageLow(data);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handImage(Intent data) {
        String path = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                path = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }
        displayImage(path);
    }
    private void handImageLow(Intent data) {
        Uri uri = data.getData();
        String path = getImagePath(uri, null);
        displayImage(path);
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath) {

        if (imagePath != null){
            File img = new File(imagePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), img);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", img.getName(),requestFile);
            NetAPI.api.file(MainActivity.token,body).enqueue(new NetAPI<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(IndexActivity.this,response.body(),Toast.LENGTH_SHORT).show();
                    NetAPI.api.netFile(MainActivity.token).enqueue(new NetAPI<List<API.NetFile>>() {
                        @Override
                        public void onResponse(Call<List<API.NetFile>> call, Response<List<API.NetFile>> response) {
                            re_sel.setAdapter(new Adapter(response.body()));
                        }
                    });
                }
            });
        }
    }
}