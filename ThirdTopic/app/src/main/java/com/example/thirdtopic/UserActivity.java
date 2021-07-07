package com.example.thirdtopic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView headTv, moneyTv, setupTv, chargeTv;
    private ImageView tReturnIv, headIv;
    private Button bReturnBtn;
    private Uri imageUri;
    private ChargeDataBase dbHelper;
    private SQLiteDatabase db;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        dbHelper = new ChargeDataBase(this, "chargeTable.db", null, 1);
        db = dbHelper.getWritableDatabase();
        initView();
        initMoney();

    }

    private void initMoney() {
        int and = 0;
        Cursor cursor = db.query("charge", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                and += cursor.getInt(cursor.getColumnIndex("money"));
            } while (cursor.moveToNext());
        }
        moneyTv.setText("账户余额：" + and + "元");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_tv_setup:
                Intent intent = new Intent(UserActivity.this, SetupUserActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.user_tv_charge:
                ChargeActivity.actionStart(UserActivity.this);
                break;
            case R.id.user_btn_return_t:
            case R.id.user_btn_return_b:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    headTv.setText(data.getStringExtra("string_return"));
                    imageUri = data.getParcelableExtra("uri_return");
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    headIv.setImageBitmap(bitmap);
                }
        }
    }

    private void initView() {
        headTv = findViewById(R.id.user_head_tv);
        moneyTv = findViewById(R.id.user_tv_money);
        setupTv = findViewById(R.id.user_tv_setup);
        chargeTv = findViewById(R.id.user_tv_charge);
        tReturnIv = findViewById(R.id.user_btn_return_t);
        headIv = findViewById(R.id.user_head_iv);
        bReturnBtn = findViewById(R.id.user_btn_return_b);
        setupTv.setOnClickListener(this);
        chargeTv.setOnClickListener(this);
        tReturnIv.setOnClickListener(this);
        bReturnBtn.setOnClickListener(this);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

}