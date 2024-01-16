package sw.biz.srv.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.bas.business.LinkmanSpecifications;
import sw.biz.enums.LinkmanEnum;
import sw.biz.srv.entity.Linkman;
import sw.biz.srv.repository.ILinkmanRepository;
import sw.biz.srv.service.ILinkmanService;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/21
 */
@Service
public class LinkmanService extends BaseBusinessBook<Linkman> implements ILinkmanService {
    public LinkmanService(IBaseRepository<Linkman> baseRepository) {
        super(baseRepository);
    }
    @Autowired
    private ILinkmanRepository iLinkmanRepository;
    @Override
    public Message update(Linkman linkman) {
        Linkman linkman1 = iLinkmanRepository.findById(linkman.getId()).get();
        if (linkman1 == null) {
            Message.success("未找到该ID内容");
        }
        linkman1.setStatus(LinkmanEnum.COMPLETED);
        iLinkmanRepository.save(linkman1);
        return Message.success("更新成功");
    }
    //设置页码数为1
    private Pageable createPageable(int page, int pageSize) {
        if (page <= 0) {
            page = 0;
        } else {
            page = page - 1;
        }
        return PageRequest.of(page, pageSize);
    }
    //搜索
    @Override
    public Message search(String keyword,String status, int page, int pageSize) {
        Pageable pageable = createPageable(page, pageSize);
        Specification<Linkman> spec = LinkmanSpecifications.searchAndProductName(keyword,status);
        Page<Linkman> result = iLinkmanRepository.findAll(spec, pageable);
        return Message.success(pageToPageable(result));

    }
    //重构返回数据
    public sw.biz.web.Pageable pageToPageable(Page<Linkman> result){
        sw.biz.web.Pageable onwerPageable = new sw.biz.web.Pageable<>();
        onwerPageable.setPage(result.getNumber()+1);
        onwerPageable.setPageSize(result.getSize());
        onwerPageable.setTotal(result.getTotalElements());
        onwerPageable.content(result.getContent());
        return onwerPageable;
    }
}
