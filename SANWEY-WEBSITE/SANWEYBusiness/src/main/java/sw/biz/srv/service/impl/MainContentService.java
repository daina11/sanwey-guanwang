package sw.biz.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.MainContent;
import sw.biz.srv.repository.IMainContentRepository;
import sw.biz.srv.service.IMainContentService;
import sw.core.web.Message;


/**
 * @author dwg
 * Date: 2023/12/12
 */
@Service
public class MainContentService  extends BaseBusinessBook<MainContent> implements IMainContentService {

    /**
     * 初始化持久层
     *
     * @param baseRepository <T>当前业务类实体
     */
    public MainContentService(IBaseRepository<MainContent> baseRepository) {
        super(baseRepository);
    }
    @Autowired
    private IMainContentRepository imainContentRepository;

    @Override
    public Message update(MainContent mainContent) {
      MainContent mainContent1 = imainContentRepository.findById(mainContent.getId()).get();
      if(mainContent1==null){
          return Message.success("未找到该ID内容");
      }
      mainContent1.setImgUrl(mainContent.getImgUrl());
      mainContent1.setTitle(mainContent.getTitle());
      mainContent1.setName(mainContent.getName());
      imainContentRepository.save(mainContent1);
        return Message.success("修改成功");
    }

    @Override
    public Message findAll() {
        return Message.success(imainContentRepository.findAllByOrderByCreateDateDesc());
    }
}
