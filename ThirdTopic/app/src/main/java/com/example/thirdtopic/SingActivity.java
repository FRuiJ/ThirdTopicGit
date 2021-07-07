package com.example.thirdtopic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SingActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar pg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        webView = findViewById(R.id.web_view);
        pg1 = (ProgressBar) findViewById(R.id.pb);
//        webView.loadUrl("file:///android_asset/sign.html");
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if(newProgress==100){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
//                }
//                else{
//                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
//                    pg1.setProgress(newProgress);//设置进度值
//                }
//
//            }
//        });
        setProgressT6();
        setWebT6();
    }

    private void setWebT6() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/sign.html");
        webView.addJavascriptInterface(SingActivity.this, "t6");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SingActivity.class);
        context.startActivity(intent);
    }

    private void setProgressT6() {
        pg1.setVisibility(View.VISIBLE);
        pg1.setProgress(0);
        new CountDownTimer(1000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                pg1.incrementProgressBy(3);
            }

            @Override
            public void onFinish() {
                pg1.setVisibility(View.GONE);
            }
        }.start();
    }

    @JavascriptInterface
    public void setP() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setProgressT6();
            }
        });
    }

    int i = 0;

    @JavascriptInterface
    public void qiandao() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (i == 0) {
                    Toast.makeText(SingActivity.this, "签到成功，积分加100", Toast.LENGTH_SHORT).show();
                    i++;
                } else {
                    Toast.makeText(SingActivity.this, "您已领取", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}