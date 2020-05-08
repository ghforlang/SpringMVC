package com.nbu.edu.cn.handler.exceptionhandler;


import com.nbu.edu.cn.exception.BaseException;
import com.nbu.edu.cn.exception.BusinessException;
import com.nbu.edu.cn.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Component
@ControllerAdvice
//@ConditionalOnWebApplication()
//@ConditionalOnMissingBean()
public class UnifiedExceptionHandler {

    @Resource
    private UnifiedMessageSource unifiedMessageSource;

    public String getMessage(BaseException e){
        String code = "response[" + e.getResponseEnum().toString() + "]";
        String message = unifiedMessageSource.getMessage(code,e.getArgs());
        return StringUtils.isBlank(message) ? e.getMessage() : message;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorResponse handleBusinessException(BaseException e){
        log.error(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }


}
