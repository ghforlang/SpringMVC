package com.nbu.edu.cn.utils.model.compare;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@Setter
//属性必须继承Comparable接口
public class GuavaHello implements Comparable<GuavaHello>{

    private String name;
    private int size;
    private long time;

    @Override
    public int compareTo(@NotNull GuavaHello o) {
        return 0;
    }
}
