package com.nbu.edu.cn.model.pair;

import com.nbu.edu.cn.model.descriminator.TimeSpanDescriminator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DateSpanPair extends TimeSpanDescriminator<Date> implements SpanPair<Date> {
    /**
     * 起始时间
     */
    private Date from;

    /**
     * 结束时间
     */
    private Date to;


    @Override
    public Date from() {
        return from;
    }

    @Override
    public Date to() {
        return to;
    }

    @Override
    public boolean isValid() {
        return Objects.nonNull(from) && Objects.nonNull(to) && from.before(to);
    }


    @Override
    public boolean hasInterSection(SpanPair<Date> sp) {
        return Objects.isNull(sp) || !isValid() || !sp.isValid() || (to.after(sp.from()) && from.before(sp.to()));
    }

    public boolean isCrossDay(){
        if(isValid()){
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTime(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(to);
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
            fromCal.setTime(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(to);
            return (!Objects.equals(fromCal.get(Calendar.YEAR),toCal.get(Calendar.YEAR)))
                    || (!Objects.equals(fromCal.get(Calendar.MONTH),toCal.get(Calendar.MONTH)));
        }
        return true;
    }

    @Override
    public boolean isCrossYear() {
        if(isValid()){
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTime(from);
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(to);
            return !Objects.equals(fromCal.get(Calendar.YEAR),toCal.get(Calendar.YEAR));
        }
        return true;
    }
}
