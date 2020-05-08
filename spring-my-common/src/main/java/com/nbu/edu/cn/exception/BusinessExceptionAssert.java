package com.nbu.edu.cn.exception;

import java.text.MessageFormat;

public interface BusinessExceptionAssert extends IResponseEnum,Assert {

    @Override
    default BaseException newException(Object... args) {
        String message = MessageFormat.format(this.getMessage(),args);
        return new BusinessException(this,args,message);
    }

    @Override
    default BaseException newException(Throwable t, Object... args){
        String message = MessageFormat.format(this.getMessage(),args);
        return new BusinessException(this,args,message,t);
    }

}
