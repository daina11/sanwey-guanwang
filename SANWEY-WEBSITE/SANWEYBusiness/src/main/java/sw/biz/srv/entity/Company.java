package sw.biz.srv.entity;

import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dwg
 * Date: 2023/12/14
 */
@Entity
@Table(name = Company.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class Company extends BaseEntity {
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_COMPANY";
    @Column(name = "FIMGURL")
    private String imgUrl;
    @Column(name = "FDESCRIPTION")
    private String description;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
