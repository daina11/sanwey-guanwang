package sw.biz.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.biz.bas.business.BaseBusinessBook;
import sw.biz.bas.business.IBaseRepository;
import sw.biz.srv.entity.Company;
import sw.biz.srv.repository.ICompanyRepository;
import sw.biz.srv.service.ICompanyService;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/12/14
 */
@Service
public class CompanyService extends BaseBusinessBook<Company> implements ICompanyService {
    /**
     * 初始化持久层
     *
     * @param baseRepository <T>当前业务类实体
     */
    public CompanyService(IBaseRepository<Company> baseRepository) {
        super(baseRepository);
    }
    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Override
    public Message update(Company company) {
        Company company1 = iCompanyRepository.findById(company.getId()).get();
        if (company1 == null) {
            return Message.success("未找到该ID内容");
        }
        company1.setName(company.getName());
        company1.setImgUrl(company.getImgUrl());
        company1.setDescription(company.getDescription());
        iCompanyRepository.save(company1);
        return Message.success("修改成功");
    }

    @Override
    public Message findAll() {
        return Message.success(iCompanyRepository.findAllByOrderByCreateDateDesc());
    }
}
