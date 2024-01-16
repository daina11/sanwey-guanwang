package sw.biz.bas.annotation;

import sw.biz.enums.SqlSplicingTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为空
 * @author wwh
 * @date 2022/6/21-17:48
 * @email 644129971@qq.com
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNull {
    SqlSplicingTypeEnum splicing() default SqlSplicingTypeEnum.AND;
    boolean isnull() default false;
    String column();
}
