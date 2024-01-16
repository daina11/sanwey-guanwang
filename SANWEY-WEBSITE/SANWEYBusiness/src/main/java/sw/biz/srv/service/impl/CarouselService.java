package sw.biz.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.Carousel;
import sw.biz.srv.repository.ICarouselRepository;
import sw.biz.srv.service.ICarouselService;
import sw.core.web.Message;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/7
 */
@Service
public class CarouselService extends BaseBusinessBook<Carousel> implements ICarouselService {

    /**
     * 初始化持久层
     *
     * @param baseRepository <T>当前业务类实体
     */
    @Autowired
    private ICarouselRepository iCarouselRepository;
    public CarouselService(IBaseRepository<Carousel> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Message saveAll(List<Carousel> carouselList) {
        try {
            iCarouselRepository.deleteAll();
            iCarouselRepository.saveAll(carouselList);
            return Message.success("添加成功");
        }catch (Exception e){
            return Message.error("添加失败");
        }
    }

    @Override
    public Message findAll() {
        List<Carousel> carouselList = iCarouselRepository.findAll();
        return Message.success(carouselList);
    }
}
