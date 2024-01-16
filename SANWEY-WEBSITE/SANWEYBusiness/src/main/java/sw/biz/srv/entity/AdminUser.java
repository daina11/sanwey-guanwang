package sw.biz.srv.entity;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dwg
 * Date: 2023/11/24
 */
@Entity
@Table(name = AdminUser.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class AdminUser extends BaseEntity {
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_ADMIN_USER";
    /**密码*/
    private String password;
    /**用户名*/
    @Unique
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
