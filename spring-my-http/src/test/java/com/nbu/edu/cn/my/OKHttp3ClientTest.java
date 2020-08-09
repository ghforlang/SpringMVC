package com.nbu.edu.cn.my;


import com.edu.nbu.cn.my.OkHttp3Client;
import okhttp3.Headers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-my-okhttp.xml")
public class OKHttp3ClientTest {

    @Resource
    private OkHttp3Client okHttp3Client;

    private static final String getURL = "https://www.baidu.com/home/msg/data/personalcontent?" +
            "num=8&indextype=manht&_req_seqid=0xb26c0c1600016f80&asyn=1&t=1596961395304&" +
            "sid=32292_1447_32300_32361_31660_32350_32046_32398_32405_32117_32090_32500";

    private static final String postURL = "";


    @Test
    public void testGetWithNoParam() throws IOException {
        String getResult =  okHttp3Client.get(getURL);
        System.out.println(getResult);
    }

    @Test
    public void testGetWithParam() throws IOException {
        String getResult = okHttp3Client.get(getURL,"");
        System.out.println(getResult);
    }

    @Test
    public void testPostWithNoParam() throws IOException {
        String getResult =  okHttp3Client.post(getURL,"");
        System.out.println(getResult);
    }

    @Test
    public void testPostWithParam() throws IOException {
        Map<String,String> headMap = new HashMap();
        headMap.put("key","12313123");
        Headers headers = Headers.of(headMap);
        String getResult =  okHttp3Client.post(getURL,"",headers);
        System.out.println(getResult);
    }
}
