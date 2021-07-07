package com.example.thirdtopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChargeRecordActivity extends AppCompatActivity {
    private Toolbar userToolbar;
    private ImageView userBtnReturnT;
    private TextView tvMoney;
    private RecyclerView layoutRv;
    private ChargeDataBase dbHelper;
    private List<String> timeList;
    private List<Integer> moneyList;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chare_record);
        dbHelper = new ChargeDataBase(this, "chargeTable.db", null, 1);
        db = dbHelper.getWritableDatabase();
        initView();
        initMoney();
        ChargeAdapter adapter = new ChargeAdapter(timeList, moneyList);
        layoutRv.setAdapter(adapter);
        userBtnReturnT.setOnClickListener(v -> {
            finish();
        });
    }


    private void initMoney() {
        timeList = new ArrayList<>();
        moneyList = new ArrayList<>();
        int and = 0;
        Cursor cursor = db.query("charge", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                timeList.add(cursor.getString(cursor.getColumnIndex("time")));
                moneyList.add(cursor.getInt(cursor.getColumnIndex("money")));
                and += cursor.getInt(cursor.getColumnIndex("money"));
            } while (cursor.moveToNext());
        }
        if (timeList.isEmpty()) {
            timeList.add("暂无充值历史记录");
        }
        tvMoney.setText("账户余额：" + and + "元");
    }

    private void initView() {
        userToolbar = (Toolbar) findViewById(R.id.user_toolbar);
        userBtnReturnT = (ImageView) findViewById(R.id.user_btn_return_t);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        layoutRv = (RecyclerView) findViewById(R.id.layout_rv);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChargeRecordActivity.class);
        context.startActivity(intent);
    }
}