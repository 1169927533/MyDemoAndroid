package com.example.a11699.mydemo.ui.userevaluation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.util.SelectPicUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserEvaluationActivity extends AppCompatActivity {
    @BindView(R.id.btn_getpicture)
    Button btn_getpicture;
    @BindView(R.id.imageView)
    ImageView imageView;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_evaluation);
        ButterKnife.bind(this);
        initPermission();
        btn_getpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPicUtil.getByAlbum(UserEvaluationActivity.this);
            }
        });
    }

    void initPermission() {
        //获取读取存储空间的权限
        if (ContextCompat.checkSelfPermission(UserEvaluationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(UserEvaluationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = SelectPicUtil.onActivityResult(UserEvaluationActivity.this, requestCode, resultCode, data);
        imageView.setImageBitmap(bitmap);
    }

}
