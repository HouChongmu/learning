package com.yolyn.stream;

import com.alibaba.fastjson.JSONObject;
import com.zhongan.scorpoin.common.ZhongAnApiClient;
import com.zhongan.scorpoin.common.ZhongAnOpenException;
import com.zhongan.scorpoin.common.dto.CommonRequest;
import com.zhongan.scorpoin.common.dto.CommonResponse;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/3/8 7:30 PM
 * @project stream-learning
 * @description
 */
public class ZhonganApiTest {
    public static void main(String[] args) throws ZhongAnOpenException {
        // ZhongAnApiClient client = new ZhongAnApiClient(env, appKey, privateKey, version);
        // env：环境参数，在dev、iTest、uat、prd中取值
        // appKey：开发者的appKey。如何获取appKey,请详见“接入流程说明”
        // privateKey：开发者私钥。如何生成开发者私钥,请详见“接入流程说明”
        // version: 服务的版本号，默认为1.0.0，测试期间版本号请与众安开发人员确认，UAT和生产必须为1.0.0
//        String appKey = "2842652df9efa1bcd75750d81d892914";
//        String privatekey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN2aLFPSrdej8SDUyUzzwd+K8e/bSAkTUJen3xM/FnwG4XpPSszvcMcIL9XOV4CL3u0gv+WfCLQRG5SwkJ0e0zyZ2ZfLQLeBEhvYNE5+o1o0wA5vwQ+s6e7aqHTWCTKKHpUqTwfaolJvRVY7NeKVjRV8PtWH/QuElM4xg7ce1JkHAgMBAAECgYEAqTLJO6s1rttvBalSld3cHomhVokwRDWqKFE1oyVdTo+WY2PdcgI0MtOOaoolB3JEYPvLaVB3Pb1+OJZjpIPftg416xexztfYxEIYQKZR2mGlC9xiiCcqWyjv462psIVV+bIldF6oR02KpnKiZMYwrclL9cw+uHyEqc+BUTk3QKECQQD9pQdvfnAR8fyj7ZtGs/nrxHxSSQXRecC1aL3mOxQJnPCDJg6DPMm6jAhhMYf7nyr1DCW0Wr0dc8fKTKPhWtCRAkEA36j41u1mLY46ZYFCXIw6uIZdkk0beSBZL1udOdjnGw77pmwuLIF7o/1Xi4Xbxb2UhzME1VCEmxIyd21XkRwcFwJBAIWBGoPN2jEn3KFwDB3P10kId6Nb+isC2bLieam4CNjyeQmeu17KXSfKUxLNXlTLt3GZvjLK2+Wnrk5FvrB7+3ECQHYtDe5iWY7Qi8J+XdYQsiY1uNNIsq95jCYdviGGnfYKIKV6m5sgf3fSsKMkA0+kMAqPxon/lVFqeAfqWk5uD9kCQQDZjeXyMdG4qpIO/ZppISHVJ9dLwWGahanO1KYdzI+Hy5U/tKTTJGkQErVZ5gUOCYzST1p+d++u0c+5ZlG3l+QV";
       String appKey="b430fe4fafab77a5ee8da2e3b526b4d4";
        String privatekey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL4ib7aGZKEBsl32ctohFRD24dosgLl8A/UjnsVTNDpOJ9Whx9gTMpPVIqQWBLtdsHK7/zcoc5IUAf9MEuNOqmGJcY7nASLY4c5fCeujgB0sJ/yqCY4eNIfdrTaCZ/2c2sas8aQNS2lzT+FBsMuYRIvqB2kbU19tJHtPykC560bhAgMBAAECgYEAm1rt5rjnDjuhr28IpzyyxtrC7g7A203+udVyDI+QtDdaCw+Wh/IfuHVFSceH9ER4QTdlMH+H9wsMGrrhEAQd6A1twWECuJanAPHqEfOtWexaVIDzj0dps3tYGmViE+I/7VNjuradBdhHOtJaSguPGfZU+3DEtEwa0QiznJ0d5PECQQDoUWwqjl/eq+yIRdTakAu0kvVv5GzoowbzJSe977hybfUBda/DJtFbqmTbbckblpXJ27LUUh1DzNT1kuVdT09tAkEA0YQw8z5rhdifcgXbd7QFKcBsTHWgIZ+fE8CcBQB0rxqWujyw8VVvnieq9xT+bwoij1X9Ehi4vfpDeeUHCW7IxQJAS8nkQ63W0Mm9jGdbnUHIz8vxjeMzNGGa7s98O5Zs0cfVWfiOsleDjSf1ZzBVn05s50FpAaMYMPotrz+Q2/P3sQJBAIm+3ivd2vUpgVKGtU6SxV44yzrNjpTsi7qBxFGphg1lbrlMk4xm/jY9oDJH1CuOvz/7aaGPLU6BjgFC6QhzlH0CQQCrs1UqpHfCpsBWtJULZ6F0HdB9mUhMy1u1R7u7IItT3R3c4ngj1KFrGqP6e3rZbgRRISB0fwEg/vZ+rSk+xurk";
        ZhongAnApiClient client = new ZhongAnApiClient("dev", appKey, privatekey, "1.0.0");

        //由于开发环境众安网关地址不定，可在ZhongAnApiClient构造方法中传入url。注意，该方法传入的url只在开发环境有效，其他环境中ZhongAnApiClient只会取默认的url。
        // String url ="http://120.27.167.36:8080/Gateway.do";
        // ZhongAnApiClient client = new ZhongAnApiClient("dev",url, appKey, privatekey, "1.0.0");

        //接口名称
        String serviceName = "zhongan.superman.open.fliggy.queryProductNo";
        CommonRequest request = new CommonRequest(serviceName);

        JSONObject map = new JSONObject();
        map.put("policyNo", "PI1574S210300256777787");
        //业务参数
//        JSONObject param = new JSONObject();
//        param.put("requestParam", map.toJSONString());
        request.setParams(map);
        //发起请求
        CommonResponse response = (CommonResponse) client.call(request);
        System.out.println(response.getBizContent());
    }
}
