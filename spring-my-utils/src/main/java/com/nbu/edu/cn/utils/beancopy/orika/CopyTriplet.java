package com.nbu.edu.cn.utils.beancopy.orika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CopyTriplet<T,U,V> {
    private T sourceField;
    private U targetField;
    private V converterId;
}
