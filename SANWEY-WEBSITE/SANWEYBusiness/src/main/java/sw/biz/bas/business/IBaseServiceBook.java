package sw.biz.bas.business;

import org.springframework.data.domain.Sort;
import sw.biz.web.Pageable;
import sw.core.web.Message;

import java.util.List;

/**
 * 业务处理统一接口
 * @author wwh
 * @date 2021/1/22
 * @email 644129971@qq.com
 */
public interface IBaseServiceBook<T> {

    T get(String id);

    List<T> list(BasePageCondition basePageCondition, Sort sort);

    Pageable<T> page(BasePageCondition condition, Pageable pageable);

    Message validNumber(String number);

    Message save(T t);

    Message delete(String id);

    Message drop(String id);

    Message enable(String id, Boolean enable);
}
