package sw.biz.bas.annotation;

import sw.biz.enums.SqlSplicingTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 之间
 * @author wwh
 * @date 2022/6/22-18:47
 * @email 644129971@qq.com
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BetweenDate {
    String column();
    SqlSplicingTypeEnum splicing() default SqlSplicingTypeEnum.AND;
}
