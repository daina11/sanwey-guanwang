package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import sw.biz.srv.dto.CompanyDto;
import sw.biz.srv.entity.Company;
import sw.biz.srv.service.ICompanyService;
import sw.biz.web.Pageable;
import sw.core.web.Message;

import javax.annotation.Resource;

/**
 * @author dwg
 * Date: 2023/12/14
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin/company")
public class PcCompanyController {
    @Resource
    private ICompanyService iCompanyService;
    @PostMapping
    public Message add(@RequestBody Company company){
        return iCompanyService.save(company);
    }
    @PutMapping
    public Message update(@RequestBody Company company){
        return iCompanyService.update(company);
    }
    @GetMapping
    public Message list(CompanyDto condition, Pageable pageable){
        return Message.success(iCompanyService.page(condition, pageable));
    }
    @DeleteMapping("/{id}")
    public Message delete(@PathVariable String id){
        return iCompanyService.delete(id);
    }

}
