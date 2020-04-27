package com.nbu.edu.cn.utils.spanpair;

import com.nbu.edu.cn.model.pair.DateSpanPair;
import com.nbu.edu.cn.model.pair.TimeStampSpanPair;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class TimeSpanPairUtils {
    /**
     * 判断时间是否有交叉
     * @param phrase1
     * @param phrase2
     * @return
     */
    public static boolean hasIntersectionForDate(DateSpanPair phrase1, DateSpanPair phrase2){
        //为空默认有重叠
        if(Objects.isNull(phrase1) || Objects.isNull(phrase2)){
            return true;
        }

        return phrase1.hasInterSection(phrase2);
    }


    /**
     * 判断时间是否有交叉(时间戳)
     * @param phrase1
     * @param phrase2
     * @return
     */
    public static boolean hasInterSectionForTimeStamp(TimeStampSpanPair phrase1, TimeStampSpanPair phrase2){
        //为空默认有重叠
        if(Objects.isNull(phrase1) || Objects.isNull(phrase2)){
            return true;
        }

        return phrase1.hasInterSection(phrase2);
    }

}
