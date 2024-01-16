package sw.biz.srv.service.impl;

import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.ContentDetail;
import sw.biz.srv.service.IContentDetailService;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2024/1/3
 */
@Service
public class ContentDetailService extends BaseBusinessBook<ContentDetail> implements IContentDetailService {
    public ContentDetailService(IBaseRepository<ContentDetail> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Message update(ContentDetail contentDetail) {
        return null;
    }
}
