package com.adbse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adbase.sdk.IAdBaseSDK;

public class MainActivity extends AppCompatActivity {

    IAdBaseSDK mSDK;    //随便怎么获取，都行，sdk内部目前是单例实现

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSDK = IAdBaseSDK.F.create();   //随便怎么获取，都行，sdk内部目前是单例实现
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

    }
}
