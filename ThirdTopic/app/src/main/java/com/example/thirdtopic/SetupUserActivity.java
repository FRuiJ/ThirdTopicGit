package com.example.thirdtopic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class SetupUserActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TAKE_PHOTO = 1;
    private ImageView tReturnIv, headIv;
    private EditText headEt;
    private Button button;
    private String headString;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_user);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                File outIv = new File(getExternalCacheDir(), "out_Iv.jpg");
//                存在就删除它，否则就新建它
                try {
                    if (outIv.exists()) {
                        outIv.delete();
                    }
                    outIv.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
//                    如果是安卓7.0以上版本，地址从文件共享器中取出
                    imageUri = FileProvider.getUriForFile(SetupUserActivity.this,
                            "com.example.thirdtopic.fileprovider", outIv);
                } else {
                    imageUri = Uri.fromFile(outIv);
                }
                //调用照像机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //Android自定义裁切输出位置 MediaStore.EXTRA_OUTPUT
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //有回调的跳转
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.btn_b:
                // 确定按钮
                headString = headEt.getText().toString();
                Intent intent1 = new Intent();
                intent1.putExtra("string_return", headString);
                intent1.putExtra("uri_return", imageUri);
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.btn_t:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //把拍得照片转为java的Bitmap对象
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        headIv.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void initView() {
        tReturnIv = findViewById(R.id.btn_t);
        headIv = findViewById(R.id.iv);
        headEt = findViewById(R.id.et);
        button = findViewById(R.id.btn_b);
        headIv.setOnClickListener(this);
        tReturnIv.setOnClickListener(this);
        button.setOnClickListener(this);
    }


}