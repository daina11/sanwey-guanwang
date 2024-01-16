package sw.biz.srv.service.impl;

import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.AdminUser;
import sw.biz.srv.service.IAdminUserService;

/**
 * @author dwg
 * Date: 2023/11/24
 */
@Service
public class AdminUserService extends BaseBusinessBook<AdminUser> implements IAdminUserService {
    public AdminUserService(IBaseRepository<AdminUser> baseRepository) {
        super(baseRepository);
    }
}
