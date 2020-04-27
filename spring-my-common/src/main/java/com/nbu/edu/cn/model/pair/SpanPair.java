package com.nbu.edu.cn.model.pair;

import java.io.Serializable;

public interface SpanPair<T> extends Serializable {

    /**
     * 起始
     * @return
     */
    T from();

    /**
     * 结束
     * @return
     */
    T to();

    /**
     * 是否有效
     * @return
     */
    boolean isValid();

    /**
     * 是否有交集
     * @param sp ,sp为空认为有交集
     * @return
     */
    boolean hasInterSection(SpanPair<T> sp);
}
