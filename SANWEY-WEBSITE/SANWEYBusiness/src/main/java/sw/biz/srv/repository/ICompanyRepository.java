package sw.biz.srv.repository;

import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.Company;

import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/14
 */
public interface ICompanyRepository extends IBaseRepository<Company> {
    List<Company> findAllByOrderByCreateDateDesc();
}
