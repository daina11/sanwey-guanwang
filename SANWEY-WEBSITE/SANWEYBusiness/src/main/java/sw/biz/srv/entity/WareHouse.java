package sw.biz.srv.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;
import sw.biz.srv.entity.WareHouseSeat;

import javax.persistence.*;
import java.util.List;

/**
 * 仓库管理
 * @author wwh
 * @date 2023/3/3-13:43
 * @email 644129971@qq.com
 */
@Entity
@Table(name = WareHouse.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
//解决在序列化 Java 对象为 JSON 格式时出现循环引用的问题。避免无限递归问题。
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WareHouse extends BaseEntity {

    /** TABLE NAME */
    public static final String TABLE = "SW_WMS_WAREHOUSE";
    /**仓库地址*/
    private String address;
    /**联系人*/
    private String linkMan;
    /**联系电话*/
    private String linkPhone;
    /**库管员id*/
    private String managerId;
    /**库管员*/
    private String managerName;
    /**货位*/
    private List<WareHouseSeat> wareHouseSeats;

    @Column(name = "FADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "FLINKMAN")
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "FLINKPHONE")
    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Column(name = "FMANAGERID")
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Column(name = "FMANAGERNAME")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @OneToMany(mappedBy = "wareHouse", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    public List<WareHouseSeat> getWareHouseSeats() {
        return wareHouseSeats;
    }

    public void setWareHouseSeats(List<WareHouseSeat> wareHouseSeats) {
        this.wareHouseSeats = wareHouseSeats;
    }
}
