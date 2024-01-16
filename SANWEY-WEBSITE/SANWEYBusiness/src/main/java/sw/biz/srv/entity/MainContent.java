package sw.biz.srv.entity;

import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dwg
 * Date: 2023/12/12
 */
@Entity
@Table(name = MainContent.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class MainContent  extends BaseEntity {
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_MAIN_CONTENT";
    /** 图片链接 */
    @Column(name = "FIMGURL")
    private String imgUrl;
    @Column(name = "FTITLE")
    private String title;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
