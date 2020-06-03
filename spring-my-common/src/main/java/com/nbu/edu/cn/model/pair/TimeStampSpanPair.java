package com.nbu.edu.cn.model.pair;

import com.nbu.edu.cn.model.descriminator.TimeSpanDescriminator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TimeStampSpanPair extends TimeSpanDescriminator<Long> implements SpanPair<Long> {
    /**
     * 起始时间戳
     */
    private Long from;
    /**
     * 结束时间戳
     */
    private Long to;

    @Override
    public Long from() {
        return from;
    }

    @Override
    public Long to() {
        return to;
    }

    @Override
    public boolean hasInterSection(SpanPair<Long> sp) {
        return Objects.isNull(sp) || !isValid() || !sp.isValid() || (to > sp.from() && from < sp.to());
    }

    @Override
    public boolean isValid() {
        return Objects.nonNull(from) && Objects.nonNull(to) && from < to;
    }


    @Override
    public boolean isCrossDay() {
        if(isValid()){
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTimeInMillis(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTimeInMillis(to);
            return (!Objects.equals(fromCal.get(Calendar.YEAR),toCal.get(Calendar.YEAR)))
                    || (!Objects.equals(fromCal.get(Calendar.MONTH),toCal.get(Calendar.MONTH)))
                    || (!Objects.equals(fromCal.get(Calendar.DAY_OF_MONTH),toCal.get(Calendar.DAY_OF_MONTH)));
        }
        return true;
    }

    @Override
    public boolean isCrossMonth() {
        if(isValid()){
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTimeInMillis(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTimeInMillis(to);
            return (!Objects.equals(fromCal.get(Calendar.YEAR),toCal.get(Calendar.YEAR)))
                    || (!Objects.equals(fromCal.get(Calendar.MONTH),toCal.get(Calendar.MONTH)));
        }
        return true;
    }

    @Override
    public boolean isCrossYear() {
        if(isValid()){
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTimeInMillis(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTimeInMillis(to);
            return !Objects.equals(fromCal.get(Calendar.YEAR),toCal.get(Calendar.YEAR));
        }
        return true;
    }
}
