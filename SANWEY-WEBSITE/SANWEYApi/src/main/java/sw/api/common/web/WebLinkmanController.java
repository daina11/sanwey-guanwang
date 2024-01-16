package sw.api.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sw.biz.enums.LinkmanEnum;
import sw.biz.srv.entity.Linkman;
import sw.biz.srv.service.ILinkmanService;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/21
 */
@RestController
@CrossOrigin
@RequestMapping("/web/site/linkman")
public class WebLinkmanController {
    @Autowired
    private ILinkmanService iLinkmanService;
    @PostMapping
    public Message saveLinkman(@RequestBody Linkman linkman){
        linkman.setStatus(LinkmanEnum.UNCOMPLETED);
        return iLinkmanService.save(linkman);
    }
}
