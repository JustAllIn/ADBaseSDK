package com.adbase.sdk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class AdBaseAuth {

    private AdBaseAuth() {
        //private constructor
    }

    /**
     * 在当前请求的基础上加入&ts参数，value为当前时间戳。然后把url的参数列表按照key升序排序，
     * 值拼接成value1|value2|value3|…的形式，用crc加密这串字符串，得到的值作为&tk参数，再追加到原有url上，用于后端校验
     *
     * @param source 原始的请求参数
     * @return 加上了健全结果之后的请求参数
     */
    public static Map<String, String> getAuth(final Map<String, String> source) {
        Map<String, String> copy = new HashMap<>(source);
        // 1.在当前请求的基础上加入&ts参数，value为当前时间戳
        copy.put("ts", String.valueOf(System.currentTimeMillis()));
        // 2.然后把url的参数列表按照key升序排序
        Set<String> keys = copy.keySet();
        String[] array = new String[keys.size()];
        array = keys.toArray(array);
        Arrays.sort(array);
        // 3. 值拼接成value1|value2|value3|…的形式
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                stringBuilder.append("|");
            }
            stringBuilder.append(copy.get(array[i]));
        }
        String result = stringBuilder.toString();
        // 4.用crc加密这串字符串
        String auth = CRCUtil.getCRCString(result);
        // 5. 得到的值作为&tk参数，再追加到原有url上
        source.put("tk", auth);
        return source;
    }

}
