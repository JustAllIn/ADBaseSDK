package com.adbase.sdk;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 从asset/crc文件里读取一些必要的信息并缓存
 */
public class CRCAssets {

    private static final String FILENAME = "crc";
    private final List<String> assetCRCInfo = new ArrayList<>();

    /**
     * @param context ctx
     * @return true-读取crc文件成功；false-失败
     */
    boolean init(Context context) {
        try {
            assetCRCInfo.clear();
            InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(FILENAME));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                assetCRCInfo.add(line);
            }

            X.log.i("assets/crc content -> " + assetCRCInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return assert/crc里的第一行
     */
    String getSeatId() {
        try {
            return assetCRCInfo.get(0);
        } catch (IndexOutOfBoundsException e) { //未init或asset文件内容异常时会报错
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @return assert/crc里的第二行
     */
    String getAppCrc() {
        try {
            return assetCRCInfo.get(1);
        } catch (IndexOutOfBoundsException e) { //未init或asset文件内容异常时会报错
            e.printStackTrace();
        }
        return "";
    }
}
