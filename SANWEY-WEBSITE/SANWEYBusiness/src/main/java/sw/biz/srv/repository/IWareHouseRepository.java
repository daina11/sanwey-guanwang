package sw.biz.srv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.WareHouse;
/**
 * @author dwg
 * Date: 2023/3/10
 */
public interface IWareHouseRepository extends IBaseRepository<WareHouse> {
    Page<WareHouse> findAll(Pageable pageable);
}
