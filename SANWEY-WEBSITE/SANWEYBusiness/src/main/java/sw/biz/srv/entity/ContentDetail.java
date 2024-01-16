package sw.biz.srv.entity;

import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dwg
 * Date: 2024/1/3
 */
@Entity
@Table(name = ContentDetail.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class ContentDetail extends BaseEntity{
    /** TABLE NAME */
    public static final String TABLE = "SW_WEBSITE_CONTENT_DETAIL";
    /**富文本内容*/
    private String content;
    /** 标题 */
    private String title;
    @Column(name = "FCONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "FTITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
