package sw.biz.web;

import org.springframework.data.domain.Sort;

import static sw.core.constant.SystemStaticConfig.defaultOrderByColumn;

/**
 * 排序
 * @author wwh
 * @date 2022/8/8-15:45
 * @email 644129971@qq.com
 */
public class OrderBy {
    /**排序字段*/
    private String orderByColumn = defaultOrderByColumn;

    /**排序方式,顺序、倒叙*/
    private Sort.Direction orderByType = Sort.Direction.DESC;

    public OrderBy() {
    }

    public OrderBy(String orderByColumn, Sort.Direction orderByType) {
        this.orderByColumn = orderByColumn;
        this.orderByType = orderByType;
    }

    public static OrderBy asc(String orderByColumn){
        return new OrderBy(orderByColumn, Sort.Direction.ASC);
    }

    public static OrderBy desc(String orderByColumn){
        return new OrderBy(orderByColumn, Sort.Direction.DESC);
    }

    public Sort orderBy() {
        return orderByType.isDescending() ? Sort.by(Sort.Order.desc(orderByColumn)) : Sort.by(Sort.Order.asc(orderByColumn));
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public Sort.Direction getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Sort.Direction orderByType) {
        this.orderByType = orderByType;
    }
}