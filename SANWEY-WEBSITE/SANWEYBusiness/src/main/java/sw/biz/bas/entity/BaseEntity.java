package sw.biz.bas.entity;

import org.hibernate.annotations.GenericGenerator;
import sw.common.sys.DateConverter;
import sw.common.util.NumberUtils;
import sw.common.util.SessionUtils;
import sw.core.spring.user.AuthUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity-底层基类
 * @author wwh
 * @date 2021/3/22
 * @email 644129971@qq.com
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /** 忽略逻辑删除的数据 */
    public static final String WHERE_CLAUSE = "FHASDELETED = 0";
    /**企业id*/
    public static final String TABLE_CTRL_UNIT_ID = "FCTRLUNITID";
    /**主键*/
    private String id;
    /**编码*/
    private String number;
    /**名称*/
    private String name;
    /**创建日期*/
    private Date createDate;
    /**创建人名称*/
    private String creator;
    /**创建人id*/
    private String creatorId;
    /**编辑日期*/
    private Date editDate;
    /**编辑人*/
    private String editor;
    /**是否删除*/
    private Boolean hasDeleted;
    /**开始日期-仅用于筛选*/
    private Date startDate;
    /**结束日期-仅用于筛选*/
    private Date endDate;
    /** 所属企业 */
    private String ctrlUnitId;

    public BaseEntity() {
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "sw.common.sys.EntityIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "FID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "FNUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "FNAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="FCREATEDATE",nullable=true)
    @Convert(converter = DateConverter.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "FCREATORID")
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Column(name = "FCREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name="FEDITDATE",nullable=true)
    @Convert(converter = DateConverter.class)
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Column(name = "FEDITOR")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Column(name = "FHASDELETED", columnDefinition = "bit default 0")
    public Boolean getHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(Boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    @Transient
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Transient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = TABLE_CTRL_UNIT_ID)
    public String getCtrlUnitId() {
        return ctrlUnitId;
    }

    public void setCtrlUnitId(String ctrlUnitId) {
        this.ctrlUnitId = ctrlUnitId;
    }

    /**实体保存之前*/
    @PrePersist
    private void beforeSave(){
        //操作用户
        AuthUser currentUser = SessionUtils.currentUser();
        if (currentUser != null){
            if(this.creator == null) this.creator = currentUser.getName();
            if(this.creatorId == null) this.creatorId = currentUser.getId();
            if(this.editor == null) this.editor = currentUser.getName();
            if(this.ctrlUnitId == null) this.ctrlUnitId = currentUser.getCuId();
        }
        //数据初始化
        if (this.number == null) this.number = NumberUtils.orderNumber();
        if (this.hasDeleted == null) this.hasDeleted = false;
        if (this.createDate == null) this.createDate = new Date();
        if (this.editDate == null) this.editDate = new Date();
    }

    /**实体更新之前*/
    @PreUpdate
    private void beforeUpdate(){
        AuthUser currentUser = SessionUtils.currentUser();
        if (currentUser != null) {
            this.editor = currentUser.getName();
        }
        this.editDate = new Date();
    }

    /** 实体删除之前 */
    @PreRemove
    public void beforeDelete() {
        //记录操作日志
    }
}
