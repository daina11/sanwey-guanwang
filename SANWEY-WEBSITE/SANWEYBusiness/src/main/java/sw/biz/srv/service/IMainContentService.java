package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.MainContent;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/12
 */
public interface IMainContentService  extends IBaseServiceBook<MainContent> {
    Message update(MainContent mainContent);

    //按照CreateTime倒叙排序
    Message findAll();
}
