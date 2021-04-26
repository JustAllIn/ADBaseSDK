package com.adbase.sdk;

import java.util.zip.CRC32;

final class CRCUtil {

    private CRCUtil() {
        //private constructor
    }

    /**
     * @param source 原始字符串
     * @return 加密后的字符串
     */
    public static String getCRCString(String source) {
        AdBaseLog.i("crc32 start, source = " + source);
        final CRC32 crc32 = new CRC32();
        crc32.update(source.getBytes());
        final String result = String.valueOf(crc32.getValue());
        AdBaseLog.i("crc32 finish, result = " + result);
        return result;
    }

}
