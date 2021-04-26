package com.adbse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adbase.sdk.IAdBaseSDK;

public class MainActivity extends AppCompatActivity implements IAdBaseSDK.ILogPrinter {

    private final StringBuilder mLogBuilder = new StringBuilder();
    private final IAdBaseSDK mSDK = IAdBaseSDK.F.create();    //随便怎么获取，都行，sdk内部目前是单例实现

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_log);
        mSDK.setLogger(this);
    }

    public void invokeSDKOpen(View view) {
        int initResult = IAdBaseSDK.F.create().open(getApplication());   //随便怎么获取，都行，sdk内部目前是单例实现

        if (initResult == 0) {
            Toast.makeText(this, "SDK init success", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SDK init failed, error = " + initResult, Toast.LENGTH_LONG).show();
        }
    }

    public void invokeSDKJoin(View view) {
        IAdBaseSDK.F.create().join("1", "15527826480");
    }

    public void invokeSDKLogin(View view) {
        if (mSDK != null) {
            mSDK.login("1", "18062439163");
        }
    }

    public void invokeSDKLogout(View view) {
        IAdBaseSDK sdk = IAdBaseSDK.F.create();
        sdk.logout();
    }

    public void invokeSDKExit(View view) {
        IAdBaseSDK.F.create().exit(getApplication());
    }

    public void saveLog(View view) {
        // TODO: 2021/4/27 复制日志到剪贴板 并保存到sd卡
    }

    @Override
    public void log(String msg) {
        Log.i("demo", msg);
        mLogBuilder.append(msg).append("\n");
        mTextView.setText(mLogBuilder.toString());
    }
}
