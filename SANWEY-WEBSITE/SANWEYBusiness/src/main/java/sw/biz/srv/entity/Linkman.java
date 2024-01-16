package sw.biz.srv.entity;

import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;
import sw.biz.enums.LinkmanEnum;

import javax.persistence.*;

/**
 * @author dwg
 * Date: 2023/12/21
 * 预约使用用户
 */
@Entity
@Table(name = Linkman.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class Linkman extends BaseEntity {
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_LINKMAN";

    private String phone;

    private String companyName;
    private LinkmanEnum status;
    @Column(name = "FPHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "FCOMPANYNAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Enumerated(value = EnumType.STRING)
    @Column(name = "FSTATUSE")
    public LinkmanEnum getStatus() {
        return status;
    }

    public void setStatus(LinkmanEnum status) {
        this.status = status;
    }
}
