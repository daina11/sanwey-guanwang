package sw.biz.srv.repository;


import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.MainContent;

import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/12
 */
public interface IMainContentRepository extends IBaseRepository<MainContent> {
    List<MainContent> findAllByOrderByCreateDateDesc();

}
