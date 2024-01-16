package sw.biz.srv.repository;

import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.AdminUser;

/**
 * @author dwg
 * Date: 2023/11/24
 */
public interface IAdminUserRepository extends IBaseRepository<AdminUser> {
    AdminUser findByUsername(String username);
}
