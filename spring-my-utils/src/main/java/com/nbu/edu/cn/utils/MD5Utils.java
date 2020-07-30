package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.exception.BaseException;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@UtilityClass
@Slf4j
public class MD5Utils {

    public static final String doFinal(String srcStr) throws BaseException {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(digest.digest(srcStr.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5加密异常,srcStr:{},msg:{}",e.getMessage());
            throw ResponseEnum.BAD_UTILS.newException(e.getMessage());
        }

    }

}
