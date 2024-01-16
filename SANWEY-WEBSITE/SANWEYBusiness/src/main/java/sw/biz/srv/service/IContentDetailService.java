package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.ContentDetail;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2024/1/3
 */
public interface IContentDetailService  extends IBaseServiceBook<ContentDetail> {
    Message update(ContentDetail contentDetail);
}
