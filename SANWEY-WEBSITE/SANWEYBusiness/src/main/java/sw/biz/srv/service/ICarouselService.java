package sw.biz.srv.service;

import sw.biz.bas.business.IBaseServiceBook;
import sw.biz.srv.entity.Carousel;
import sw.core.web.Message;

import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/7
 */
public interface ICarouselService  extends IBaseServiceBook<Carousel> {
    Message saveAll(List<Carousel> carouselList);
    Message findAll();
}
