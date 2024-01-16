package sw.biz.bas.business;

import org.springframework.data.jpa.domain.Specification;
import sw.biz.enums.LinkmanEnum;
import sw.biz.srv.entity.Linkman;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dwg
 * 联系人查询构造器
 * Date: 2023/12/28
 */
public class LinkmanSpecifications {
    public static Specification<Linkman> searchAndProductName(String keyword,String status) {
        return (root, query, criteriaBuilder) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            // 添加关键字查询条件
            if (keyword != null && !keyword.isEmpty() && !"null".equals(keyword)) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + keyword + "%"),
                        criteriaBuilder.like(root.get("phone"), "%" + keyword + "%"),
                        criteriaBuilder.like(root.get("companyName"), "%" + keyword + "%")
                ));
            }
            //添加status查询条件为相等 为空的时候不查询这个条件
            if (status != null && !status.isEmpty()&&status!=" ") {
                predicates.add(criteriaBuilder.equal(root.get("status"), LinkmanEnum.valueOf(status)));
            }
            // 排序
            query.orderBy(
                    criteriaBuilder.desc(root.get("createDate"))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
