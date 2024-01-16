package sw.biz.bas.annotation;

import sw.biz.enums.SqlSplicingTypeEnum;

import javax.persistence.criteria.JoinType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 外表链接查询
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Join {
    SqlSplicingTypeEnum splicing() default SqlSplicingTypeEnum.AND;

    JoinType JoinType() default JoinType.LEFT;

    String table();

    String column();
}
