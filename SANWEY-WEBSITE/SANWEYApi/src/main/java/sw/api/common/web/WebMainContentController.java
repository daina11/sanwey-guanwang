package sw.api.common.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.biz.srv.service.IMainContentService;
import sw.core.web.Message;

import javax.annotation.Resource;

/**
 * @author dwg
 * Date: 2023/12/13
 */
@RestController
@CrossOrigin
@RequestMapping("/web/site/mc")
public class WebMainContentController {
    @Resource
    private IMainContentService iMainContentService;
    @GetMapping
    public Message findAll(){
        return iMainContentService.findAll();
    }
}
