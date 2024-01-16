package sw.api.common.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.biz.srv.service.ICarouselService;
import sw.core.web.Message;

import javax.annotation.Resource;

/**
 * @author dwg
 * Date: 2023/12/8
 */
@RestController
@CrossOrigin
@RequestMapping("/web/site/carousel")
public class WebCarouselController {
    @Resource
    private ICarouselService iCarouselService;
    @GetMapping("/list")
    public Message list(){
        return iCarouselService.findAll();
    }
}
