package sw.biz.bas.business;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.data.jpa.domain.Specification;
import sw.biz.bas.annotation.Join;
import sw.biz.bas.annotation.*;
import sw.biz.enums.SqlSplicingTypeEnum;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建查询Sql语句
 *
 * @author wwh
 * @date 2021/2/8
 * @email 644129971@qq.com
 */
@Log4j2
public class SelectSqlBuilder {

    public static Specification specificationBuilder(BasePageCondition condition) {
        try {
            return specificationHandle(condition);
        } catch (Exception e) {
            log.error("构建查询Sql语句异常", e);
            return null;
        }
    }

    private static Specification specificationHandle(BasePageCondition condition) {
        Specification specification = new Specification() {
            @SneakyThrows
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();//查询构造体
                List<Field> fields = FieldUtils.getAllFieldsList(condition.getClass());//反射
                for (Field field : fields) {
                    Predicate predicate = selectSqlHandle(condition, field, root, criteriaBuilder);
                    if (predicate != null){
                        predicates.add(predicate);
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return specification;
    }

    private static Predicate selectSqlHandle(BasePageCondition condition, Field field, Root root, CriteriaBuilder criteriaBuilder) throws IllegalAccessException {
        field.setAccessible(true);//获取private私有属性
        if (field.get(condition) != null && StringUtils.isNotBlank(field.get(condition).toString())) {//映射获取设值了的字段
            if (field.isAnnotationPresent(Equal.class)) {//等于
                Equal equal = field.getAnnotation(Equal.class);//映射注解
                Path path = pathBuilder(equal.column(), root);
                Predicate p = criteriaBuilder.equal(path, field.get(condition));//查询设值
                return extracted(criteriaBuilder, equal.splicing(), p);//sql语句链接
            } else if (field.isAnnotationPresent(Like.class)) {//模糊查询
                Like like = field.getAnnotation(Like.class);
                StringBuffer column = new StringBuffer("%").append(field.get(condition).toString()).append("%");
                Path path = pathBuilder(like.column(), root);
                Predicate p = criteriaBuilder.like(path, column.toString());//查询设值
                return extracted(criteriaBuilder, like.splicing(), p);//sql语句链接
            }else if (field.isAnnotationPresent(IsNull.class)) {//非空
                IsNull aNull = field.getAnnotation(IsNull.class);
                Boolean isNull = aNull.isnull();
                Path path = pathBuilder(aNull.column(), root);
                Predicate p = isNull ? criteriaBuilder.isNull(path) : criteriaBuilder.isNotNull(path);
                return extracted(criteriaBuilder, aNull.splicing(), p);//sql语句链接
            }else if (field.isAnnotationPresent(In.class)){//包含
                In in = field.getAnnotation(In.class);
                Path path = pathBuilder(in.column(), root);
                CriteriaBuilder.In<Object> objectIn = criteriaBuilder.in(path);
                Object[] o = (Object[]) field.get(condition);
                for (int i = 0; i < o.length; i++) {
                    objectIn.value(o[i]);
                }
                return extracted(criteriaBuilder, in.splicing(), objectIn);//sql语句链接
            }else if (field.isAnnotationPresent(Join.class)){//外表链接
                Join join = field.getAnnotation(Join.class);
                javax.persistence.criteria.Join tableJoin = root.join(join.table(), join.JoinType());
                Predicate p = criteriaBuilder.equal(tableJoin.get(join.column()), field.get(condition));//查询设值
                return extracted(criteriaBuilder, join.splicing(), p);//sql语句链接
            }else if (field.isAnnotationPresent(BetweenDate.class)) {//之间
                BetweenDate between = field.getAnnotation(BetweenDate.class);
                Path path = pathBuilder(between.column(), root);
                BaseDateCondition baseDateCondition = (BaseDateCondition) field.get(condition);
                if (baseDateCondition.getStartDate() == null || baseDateCondition.getEndDate() == null) return null;
                Predicate p = criteriaBuilder.between(path, baseDateCondition.getStartDate(), DateUtils.addDays(baseDateCondition.getEndDate(), 1));//查询设值
                return extracted(criteriaBuilder, between.splicing(), p);//sql语句链接
            }
        }
        return null;
    }

    /**指定映射字段名*/
    private static Path pathBuilder(String column, Root root){
        if (StringUtils.isNotBlank(column)){
            String[] columns = column.split("\\.");
            if (columns.length == 1){
                return root.get(columns[0]);
            }else if (columns.length == 2){
                return root.get(columns[0]).get(columns[1]);
            }
        }
        return root;
    }

    /**sql条件查询链接符*/
    private static Predicate extracted(CriteriaBuilder criteriaBuilder, SqlSplicingTypeEnum sqlSplicingTypeEnum, Predicate p) {
        if (sqlSplicingTypeEnum.compareTo(SqlSplicingTypeEnum.AND) == 0) {
            return criteriaBuilder.and(p);
        } else if (sqlSplicingTypeEnum.compareTo(SqlSplicingTypeEnum.OR) == 0) {
            return criteriaBuilder.or(p);
        } else {
            return null;
        }
    }
}
