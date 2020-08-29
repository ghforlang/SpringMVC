package com.nbu.edu.cn.utils.beancopy.customconverter;

import ma.glasnost.orika.converter.BidirectionalConverter;

public abstract class BaseConverter<S,D> extends BidirectionalConverter<S,D> {
    //抽象方法要求子类实现
}
