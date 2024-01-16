package sw.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 编码生成工具
 * @author wwh
 * @date 2021/3/5
 * @email 644129971@qq.com
 */
public class NumberUtils {

    public static String orderNumber(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    public static String randomOrderNumber(){
        return new StringBuffer().append(String.format("%1$ty%1$tm%1$td%1$tH%1$tM%1$tS", new Date())).append(String.valueOf(RandomUtils.nextLong(1000L, 9999L))).toString();
    }

    public static String orderNumber(String pre){
        return new StringBuffer(pre).append(orderNumber()).toString();
    }

}
