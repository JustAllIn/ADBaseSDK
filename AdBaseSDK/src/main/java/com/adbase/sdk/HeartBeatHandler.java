package com.adbase.sdk;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * sdk内部的心跳定时器
 */
class HeartBeatHandler extends Handler {

    private static final long HEART_BEAT_GAP = 60 * 1000;
    private static final int EVENT_HEART_BEAT = 59;

    private final WeakReference<ApiProxy> weakReference;
    private final String alive_id;

    public HeartBeatHandler(String alive_id, ApiProxy heartBeat) {
        this.weakReference = new WeakReference<>(heartBeat);
        this.alive_id = alive_id;
    }

    /**
     * 开始心跳
     */
    public void start() {
        if (this.hasMessages(EVENT_HEART_BEAT)) {
            return;
        }
        this.sendEmptyMessage(EVENT_HEART_BEAT);
    }

    /**
     * 结束心跳
     */
    public void stop() {
        if (!this.hasMessages(EVENT_HEART_BEAT)) {
            return;
        }

        this.removeMessages(EVENT_HEART_BEAT);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        ApiProxy apiProxy = weakReference.get();
        if (apiProxy == null) {
            return;
        }

        if (msg.what == EVENT_HEART_BEAT) {
            X.log.i("heartbeat ============> start");
            //接口请求发送心跳
            apiProxy.heartBeat(alive_id).enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    X.log.i("heartbeat ============> success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    X.log.i("heartbeat ============> error");
                }
            });
            //60s后再发心跳
            sendEmptyMessageDelayed(EVENT_HEART_BEAT, HEART_BEAT_GAP);
        }
    }
}
