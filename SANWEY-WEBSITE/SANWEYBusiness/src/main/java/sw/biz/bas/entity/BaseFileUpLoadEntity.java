package sw.biz.bas.entity;

import org.springframework.util.StringUtils;
import sw.common.sys.DateConverter;
import sw.common.util.SessionUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 文件上传-基类
 * @author wwh
 * @date 2021/2/3
 * @email 644129971@qq.com
 */
@Embeddable
@MappedSuperclass
public class BaseFileUpLoadEntity {
    /**文件名称*/
    private String name;
    /**文件路径*/
    private String url;
    /**文件大小*/
    private String size;
    /**上传人*/
    private String creator;
    /**上传日期*/
    private Date createDate;

    @Column(name = "FNAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FURL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "FSIZE")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "FCREATOR")
    public String getCreator() {
        return !StringUtils.hasLength(this.creator) && SessionUtils.currentUser() != null ? SessionUtils.currentUser().getUsername() : creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name="FCREATEDATE",nullable=true)
    @Convert(converter = DateConverter.class)
    public Date getCreateDate() {
        return this.createDate == null ? new Date() : createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
