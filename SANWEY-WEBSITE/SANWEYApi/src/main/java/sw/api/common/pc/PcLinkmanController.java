package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import sw.biz.srv.dto.LinkmanDto;
import sw.biz.srv.entity.Linkman;
import sw.biz.srv.service.ILinkmanService;
import sw.biz.srv.service.IMainContentService;
import sw.biz.web.Pageable;
import sw.core.web.Message;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/22
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin/linkman")
public class PcLinkmanController {
    @Resource
    private ILinkmanService iLinkmanService;
    @GetMapping
    public Message list(LinkmanDto linkmanDto,  Pageable pageable){
        return Message.success(iLinkmanService.page(linkmanDto, pageable));
    }
    @PutMapping
    public Message update(@RequestBody Linkman linkman){
        return iLinkmanService.update(linkman);

    }
    @DeleteMapping("/{id}")
    public Message delete(@PathVariable String id){
        return iLinkmanService.delete(id);
    }
    @GetMapping("/search")
    public Message search( @RequestParam("searchword") String keyword,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iLinkmanService.search(keyword,status,page,pageSize);

    }
}
