package com.example.thirdtopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ChargeActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Toolbar userToolbar;
    private ImageView userBtnReturnT;
    private TextView tvBalance;
    private RadioGroup rg;
    private RadioButton rb50;
    private RadioButton rb100;
    private RadioButton rb200;
    private RadioButton rbX;
    private EditText etX;
    private Button btnIn;
    private Button btn2;

    private ChargeDataBase dbHelper;
    private Boolean custom = false;
    private int xz_money = 50;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        dbHelper = new ChargeDataBase(this, "chargeTable.db", null, 1);
        db = dbHelper.getWritableDatabase();
        initView();
        initMoney();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_50:
                etX.setVisibility(View.GONE);
                custom = false;
                xz_money = 50;
                break;
            case R.id.rb_100:
                etX.setVisibility(View.GONE);
                custom = false;
                xz_money = 100;
                break;
            case R.id.rb_200:
                etX.setVisibility(View.GONE);
                custom = false;
                xz_money = 200;
                break;
            case R.id.rb_x:
                etX.setVisibility(View.VISIBLE);
                custom = true;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_in:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time = simpleDateFormat.format(new java.util.Date());
                if (custom == true) {
                    xz_money = Integer.parseInt(etX.getText().toString());
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("time", time);
                values.put("money", xz_money);
                db.insert("charge", null, values);
                values.clear();
                initMoney();
                break;
            case R.id.btn_2:
                ChargeRecordActivity.actionStart(ChargeActivity.this);
                break;
            case R.id.user_btn_return_t:
                finish();
                break;
        }
    }

    private void initMoney() {
        int and = 0;
        Cursor cursor = db.query("charge", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                and += cursor.getInt(cursor.getColumnIndex("money"));
            } while (cursor.moveToNext());
        }
        tvBalance.setText("账户余额：" + and + "元");
    }

    private void initView() {
        userToolbar = (Toolbar) findViewById(R.id.user_toolbar);
        userBtnReturnT = (ImageView) findViewById(R.id.user_btn_return_t);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb50 = (RadioButton) findViewById(R.id.rb_50);
        rb100 = (RadioButton) findViewById(R.id.rb_100);
        rb200 = (RadioButton) findViewById(R.id.rb_200);
        rbX = (RadioButton) findViewById(R.id.rb_x);
        etX = (EditText) findViewById(R.id.et_x);
        btnIn = (Button) findViewById(R.id.btn_in);
        btn2 = (Button) findViewById(R.id.btn_2);

        userBtnReturnT.setOnClickListener(this);
        rb50.setOnClickListener(this);
        rb100.setOnClickListener(this);
        rb200.setOnClickListener(this);
        rbX.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
        btnIn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChargeActivity.class);
        context.startActivity(intent);
    }


}