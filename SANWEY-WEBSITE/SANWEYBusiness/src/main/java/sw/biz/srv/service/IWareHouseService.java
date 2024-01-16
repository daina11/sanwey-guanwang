package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.WareHouse;
import sw.core.web.Message;
/**
 * @author dwg
 * Date: 2023/3/10
 */

public interface IWareHouseService  extends IBaseServiceBook<WareHouse> {
    Message addWareHouse(WareHouse wareHouse);

}
