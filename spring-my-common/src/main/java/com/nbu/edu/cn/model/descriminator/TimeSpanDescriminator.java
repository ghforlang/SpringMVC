package com.nbu.edu.cn.model.descriminator;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class TimeSpanDescriminator<T> implements Descriminator<T>{

    /**
     * 是否跨天
     * @return
     */
    public boolean isCrossDay(){
        return false;
    }

    /**
     * 是否跨月
     * @return
     */
    public boolean isCrossMonth(){
        return false;
    }


    /**
     * 是否跨年
     * @return
     */
    public boolean isCrossYear(){
        return false;
    }
}
