package com.example.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * junit5测试
 * <p>
 * Created by czz on 2020/03/27.
 */
public class TempHttpTests {

    final String host = "http://127.0.0.1:8090";

    final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjA1OTItNjA5MDQxMiIsImxvZ2luTmFtZSI6ImFkbWluIiwiZXhwIjoxNTg1Nzk2MjY5LCJ1c2VyaWQiOjF9.WuRN7vbvuK1LxRsckXKRoBRUPMWQ09Tes1Hzl466xE4";

    /**
     * testGet
     */
    @Test
    void testGet() {
        String url = host + "/api/v1/roles";
        //================================
        String result = HttpRequest.get(url)
                .header("Authorization", token)
                // .form(Dict.create().set("p", "2").set("s", 20))
                .execute().body();
        String prettyJson = JSONUtil.toJsonPrettyStr(result);
        Console.log(url + "\r\n-------------------------");
        Console.log(prettyJson);
    }

    @Test
    void testXlogin() {
        String url = "http://114.115.232.199:2020" + "/api/app/v1/public/loginWithAccount";
        //---------------------
        Dict dt = Dict.create()
                .set("account", "15170707553")
                .set("password", "6666666666");
        String json = JSONUtil.toJsonStr(dt);
        //================================
        String result = HttpRequest.post(url)
                .body(json)
                .execute().body();
        String prettyJson = JSONUtil.toJsonPrettyStr(result);
        Console.log(url + "\r\n-------------------------");
        Console.log(prettyJson);

        int code = JSONUtil.parseObj(result).getInt("code");
        assertTrue(StrUtil.isNotBlank(result));
        assertEquals(200, code);
    }
}