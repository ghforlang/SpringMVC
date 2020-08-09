package com.edu.nbu.cn.my;


import com.nbu.edu.cn.constant.HttpConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
public class OkHttp3Client {

    /**
     * 连接超时时间
     */
    private Long connectionTimeoutMills;
    /**
     * 读取超时时间
     */
    private Long readTimeoutMills;

    /**
     * 写入超时时间
     */
    private Long writeTimeoutMills;
    /**
     * 最大空闲连接数
     */
    private Integer maxIdleConnections;

    /**
     * 保持连接时长
     */
    private Long keepAliveDurationMills;

    /**
     * 缓存文件路径
     */
    private String cacheFilesDir;

    /**
     * 缓存文件大小
     */
    private Long cacheFileSize;

    private OkHttpClient httpClient;


    public void buildHttpClient(){
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(connectionTimeoutMills, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeoutMills,TimeUnit.MILLISECONDS)
                .readTimeout(readTimeoutMills,TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(maxIdleConnections,keepAliveDurationMills,TimeUnit.MILLISECONDS))
                .cache(new Cache(new File(cacheFilesDir),cacheFileSize))
                .build();
    }

    public String get(String url) throws IOException {
        return get(url,null);
    }

    public String get(String url,String param) throws IOException {
        String getUrl = Optional.ofNullable(param).map(p -> url + HttpConstants.URL_JOIN + param).orElse(url);
        Request getRequest = new Request.Builder().url(getUrl).build();
        Response response = httpClient.newCall(getRequest).execute();
        return response.body().string();
    }

    public String post(String url,String param) throws IOException {
        return post(url,param,null);
    }

    public String post(String url,String param,Headers headers) throws IOException {
        RequestBody requestBody = RequestBody.create(param,HttpConstants.JSON);
        Request.Builder builder = new Request.Builder();
        if(Objects.nonNull(headers)){
            builder = builder.headers(headers);
        }
        Request postRequest = builder.post(requestBody).url(url).build();
        Response response = httpClient.newCall(postRequest).execute();
        return response.body().string();
    }

}
