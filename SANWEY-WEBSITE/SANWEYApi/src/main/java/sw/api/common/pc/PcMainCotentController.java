package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import sw.biz.srv.entity.MainContent;
import sw.biz.srv.service.IMainContentService;
import sw.core.web.Message;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dwg
 * Date: 2023/12/12
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin/mc")
public class PcMainCotentController {
    @Resource
    private IMainContentService iMainContentService;
    @PostMapping
    public Message add(@RequestBody MainContent mainContent){
        return iMainContentService.save(mainContent);
    }
    @DeleteMapping("/{id}")
    public Message cancelSub(@PathVariable("id") String id){
        return iMainContentService.delete(id);
    }
    @PutMapping
    public Message update(@RequestBody MainContent mainContent){
        return iMainContentService.update(mainContent);
    }
    @GetMapping
    public Message findAll(){
        return iMainContentService.findAll();
    }
}
