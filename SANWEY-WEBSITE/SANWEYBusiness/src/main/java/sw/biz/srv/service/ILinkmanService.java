package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.Linkman;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/21
 */
public interface ILinkmanService  extends IBaseServiceBook<Linkman> {
    Message update(Linkman linkman);

    Message search(String keyword,String status, int page, int pageSize);
}
