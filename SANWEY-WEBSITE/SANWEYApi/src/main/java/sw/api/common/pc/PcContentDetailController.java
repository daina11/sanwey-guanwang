package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import sw.biz.srv.dto.ContentDetailDto;
import sw.biz.srv.entity.ContentDetail;
import sw.biz.srv.service.IContentDetailService;
import sw.biz.web.Pageable;
import sw.core.web.Message;

import javax.annotation.Resource;

/**
 * @author dwg
 * Date: 2024/1/3
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin/contentDetail")
public class PcContentDetailController {
    @Resource
    private IContentDetailService iContentDetailService;
    @GetMapping
    public Message list(ContentDetailDto contentDetailDto,  Pageable pageable){
        return Message.success(iContentDetailService.page(contentDetailDto, pageable));

    }
    @GetMapping("/{id}")
    public Message get(@PathVariable String id){
        return Message.success(iContentDetailService.get(id));
    }
    @PutMapping
    public Message update(@RequestBody ContentDetail contentDetail){
        return iContentDetailService.update(contentDetail);
    }
    @DeleteMapping("/{id}")
    public Message delete(@PathVariable String id){
        return iContentDetailService.delete(id);
    }
    @PostMapping
    public Message add(@RequestBody ContentDetail contentDetail){
        return iContentDetailService.save(contentDetail);
    }
}
