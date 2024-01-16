package sw.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * @author wwh
 * @date 2022/1/7
 * @email 644129971@qq.com
 */
public class DateUtils {

    /**周第一天*/
    public static Calendar getThisWeekFirstDay(Date selectDate) {
        Calendar c = Calendar.getInstance();
        if (selectDate != null){
            c.setTime(selectDate);
        }
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    /**周最后一天*/
    public static Calendar getThisWeekLastDay(Date selectDate) {
        Calendar c = Calendar.getInstance();
        if (selectDate != null){
            c.setTime(selectDate);
        }
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0){
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        c.set(Calendar.HOUR, 24);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }
}
