package sw.biz.srv.service.impl;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.WareHouse;
import sw.biz.srv.entity.WareHouseSeat;
import sw.biz.srv.repository.IWareHouseRepository;
import sw.biz.srv.service.IWareHouseService;
import sw.core.web.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwg
 * Date: 2023/3/10
 */
@Repository
public class WareHouseService  extends BaseBusinessBook<WareHouse> implements IWareHouseService {
    @Autowired
    private IWareHouseRepository wareHouseRepository;
    public WareHouseService(IBaseRepository<WareHouse> baseRepository) {
        super(baseRepository);
    }


    @Override
    /*新建仓库*/
    public Message addWareHouse(WareHouse wareHouse){
        WareHouse warehouseEntity  = new WareHouse();
        warehouseEntity.setCreator(wareHouse.getCreator());
        warehouseEntity.setManagerName(wareHouse.getManagerName());
        warehouseEntity.setLinkMan(wareHouse.getLinkMan());
        warehouseEntity.setLinkPhone(wareHouse.getLinkPhone());
        warehouseEntity.setAddress(wareHouse.getAddress());
        warehouseEntity.setCreatorId(wareHouse.getCreatorId());
        //warehouseEntity.setCtrlUnitId(wareHouse.getCtrlUnitId());
        //warehouseEntity.setEditor(wareHouse.getEditor());
        warehouseEntity.setName(wareHouse.getName());
        warehouseEntity.setManagerId(wareHouse.getManagerId());

        //创建一个新的仓库库位列表
        List<WareHouseSeat> warehouseSeatEntityList = new ArrayList<>();
        List<WareHouseSeat> wareHouseSeatList = wareHouse.getWareHouseSeats();
        for (WareHouseSeat wareHouseSeat : wareHouseSeatList) {
            WareHouseSeat warehouseSeatEntity = new WareHouseSeat();
            /*warehouseSeatEntity.setCreatorId(wareHouseSeat.getCreatorId());
            所属企业
            warehouseSeatEntity.setCtrlUnitId(wareHouseSeat.getCtrlUnitId());
            编辑人
            warehouseSeatEntity.setEditor(wareHouseSeat.getEditor());
            编码
            warehouseSeatEntity.setNumber(wareHouseSeat.getNumber());*/
            warehouseSeatEntity.setCreator(wareHouseSeat.getCreator());
            warehouseSeatEntity.setName(wareHouseSeat.getName());
            warehouseSeatEntity.setWareHouse(warehouseEntity);
            warehouseSeatEntityList.add(warehouseSeatEntity);
        }
        // 设置仓库库位列表
        warehouseEntity.setWareHouseSeats(warehouseSeatEntityList);
        if (wareHouse == null) {
            return Message.error("添加的仓库信息不能为空");
        }
        if (StringUtils.isBlank(wareHouse.getName())) {
            return Message.error("仓库名称不能为空");
        }
        if (StringUtils.isBlank(wareHouse.getWareHouseSeats().toString())) {
            return Message.error("库位信息不能为空");
        }
        try {
            WareHouse savedWarehouse = wareHouseRepository.save(warehouseEntity);
            return Message.success(savedWarehouse);
        } catch (Exception e) {
            return Message.error("添加仓库信息出错：" + e.getMessage());
        }

    }
}
