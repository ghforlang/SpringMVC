package com.nbu.edu.cn.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@UtilityClass
public class JackSonUtils{

    private static final ObjectMapper mapper = new ObjectMapper();

    public static  String toJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化异常:{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObj(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString,clazz);
        } catch (IOException e) {
            log.error("json反序列化异常:{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
