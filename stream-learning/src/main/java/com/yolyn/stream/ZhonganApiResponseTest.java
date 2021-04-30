package com.yolyn.stream;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/3/9 2:18 PM
 * @project stream-learning
 * @description
 */
public class ZhonganApiResponseTest {
    public static void main(String[] args) {
        String response="{\n" +
                "    \"requestParam\": \"{\\\"campaignDefId\\\":210000630006,\\\"packageDefId\\\":51423302}\",\n" +
                "    \"appKey\": \"b430fe4fafab77a5ee8da2e3b526b4d4\"\n" +
                "}";

        JSONObject reqJson = JSONObject.parseObject(response);

    }
}
