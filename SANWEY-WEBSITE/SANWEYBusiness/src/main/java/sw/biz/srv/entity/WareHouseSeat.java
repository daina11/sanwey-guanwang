package sw.biz.srv.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import sw.biz.bas.entity.BaseEntity;
import sw.biz.srv.entity.WareHouse;

import javax.persistence.*;

/**
 * 货位档案
 * @author wwh
 * @date 2023/3/6-16:47
 * @email 644129971@qq.com
 */
@Entity
@Table(name = WareHouseSeat.TABLE)
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class WareHouseSeat extends BaseEntity {

    /** TABLE NAME */
    public static final String TABLE = "SW_WMS_WAREHOUSE_SEAT";
    /**所属仓库*/
    private WareHouse wareHouse;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="FWAREHOUSE",nullable=true)
    @Where(clause = WareHouse.WHERE_CLAUSE)
    @NotFound(action= NotFoundAction.IGNORE)
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }
}
