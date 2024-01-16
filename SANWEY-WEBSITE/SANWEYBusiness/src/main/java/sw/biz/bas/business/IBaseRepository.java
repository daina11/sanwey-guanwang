package sw.biz.bas.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据持久化
 * @author wwh
 * @date 2021/1/22
 * @email 644129971@qq.com
 */
public interface IBaseRepository<T> extends JpaRepository<T, String> , JpaSpecificationExecutor<T> {
}
