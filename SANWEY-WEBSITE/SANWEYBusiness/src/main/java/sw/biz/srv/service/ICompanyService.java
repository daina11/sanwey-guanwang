package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.Company;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/14
 */
public interface ICompanyService extends IBaseServiceBook<Company> {
    Message update(Company company);

    Message findAll();
}
