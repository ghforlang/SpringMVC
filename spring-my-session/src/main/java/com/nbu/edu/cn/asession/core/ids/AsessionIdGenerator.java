package com.nbu.edu.cn.asession.core.ids;

import com.nbu.edu.cn.exception.BaseException;
import com.nbu.edu.cn.utils.MD5Utils;
import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@UtilityClass
public class AsessionIdGenerator {

    private static final AtomicLong STEP_LEN = new AtomicLong(0L);

    public static final String genId(){
        String needEncrytStr = UUID.randomUUID().toString() + System.currentTimeMillis() + STEP_LEN.incrementAndGet();
        try {
            return MD5Utils.doFinal(needEncrytStr);
        } catch (BaseException e) {
            e.printStackTrace();
        }finally {
            return needEncrytStr;
        }
    }

}
