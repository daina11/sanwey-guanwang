package sw.biz.srv.entity;

import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dwg
 * Date: 2023/12/7
 */
@Entity
@Table(name = Carousel.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class Carousel extends BaseEntity {
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_CAROUSEL";
    /** 图片链接 */
    @Column(name = "FIMGURL")
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
