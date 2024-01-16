package sw.api.common.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.biz.srv.service.ICompanyService;
import sw.core.web.Message;

import javax.annotation.Resource;

/**
 * @author dwg
 * Date: 2023/12/18
 */
@RestController
@CrossOrigin
@RequestMapping("/web/site/company")
public class WebCompanyController {
    @Resource
    private ICompanyService iCompanyService;
    @GetMapping
    public Message list(){
        return Message.success(iCompanyService.findAll());
    }
}
