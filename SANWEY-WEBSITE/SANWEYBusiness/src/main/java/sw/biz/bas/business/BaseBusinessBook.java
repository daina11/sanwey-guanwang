package sw.biz.bas.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import sw.biz.web.Pageable;
import sw.common.util.SessionUtils;
import sw.core.spring.user.AuthUser;
import sw.core.web.Message;
import sw.core.web.SystemException;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 业务处理基类
 * @author wwh
 * @date 2021/1/22
 * @email 644129971@qq.com
 */
public abstract class BaseBusinessBook<T> implements IBaseServiceBook<T> {

    /**运行日志*/
    private static final Logger logger = LoggerFactory.getLogger(BaseBusinessBook.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    private String TABLE_FIELD_NAME = "TABLE";

    /**当前实体*/
    protected Class<T> entityClass;

    /**表名*/
    private String tableName;

    private IBaseRepository<T> baseRepository;

    /**
     * 初始化持久层
     * @param baseRepository <T>当前业务类实体
     */
    public BaseBusinessBook(IBaseRepository<T> baseRepository) {
        getEntityClass();
        getTable();
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public T get(String id) {
        if (StringUtils.isNotBlank(id)) {
            Optional<T> t = baseRepository.findById(id);
            if (t.isPresent()) {
                return baseRepository.findById(id).get();
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> list(BasePageCondition basePageCondition, Sort sort) {
        Specification specification = SelectSqlBuilder.specificationBuilder(basePageCondition);
        return baseRepository.findAll(specification, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Pageable<T> page(BasePageCondition condition, Pageable pageable) {
        try {
            /**----查询的Vo属性名称必须和实体字段名称一一对应-----*/
            Specification specification = SelectSqlBuilder.specificationBuilder(condition);
            /**----构建Sql查询语句-----*/
            Page<T> page = baseRepository.findAll(specification,  PageRequest.of(pageable.selectPage(), pageable.getPageSize(), pageable.getOrderBy().orderBy()));
            /**----重新构建返回数据-----*/
            return pageable.page(page.getNumber()).pageSize(page.getSize()).total(page.getTotalElements()).content(page.getContent());
        } catch (Exception e) {
            logger.error("获取分页数据出毛病了", e);
            return new Pageable<T>();
        }
    }

    @Override
    public Message validNumber(String number) {
        StringBuffer sql = new StringBuffer();
        AuthUser user = SessionUtils.currentUser();
        if (user == null) throw new SystemException(400, "未登录");
        sql.append("select fid from ").append(getTable()).append(" where fnumber = '").append(number).append("' and FHASDELETED = 0 and FCTRLUNITID = '").append(user.getCuId()).append("'");
        List<Map<String, Object>>  resultMaps = jdbcTemplate.queryForList(sql.toString());
        if (resultMaps.size() > 0){
            return Message.warning("编号已经存在");
        }
        return Message.success("验证通过");
    }

    @Override
    @Transactional(readOnly = false)
    public Message save(T t) {
        return Message.success(baseRepository.save(t));
    }

    @Override
    public Message delete(String id) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(getTable()).append(" set FHASDELETED = true where FID = '").append(id).append("'");
        jdbcTemplate.update(sql.toString());
        return jdbcTemplate.update(sql.toString()) == 1 ? Message.success("删除成功") : Message.warning("删除失败");
    }

    @Override
    public Message drop(String id) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM ").append(getTable()).append(" WHERE FID = '").append(id).append("'");
        jdbcTemplate.update(sql.toString());
        return jdbcTemplate.update(sql.toString()) == 1 ? Message.success("删除成功") : Message.warning("删除失败");
    }

    @Override
    public Message enable(String id, Boolean enable) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(getTable()).append(" set").append(" FHASENABLED =").append(enable).append(" where FID = '").append(id).append("'");
        jdbcTemplate.update(sql.toString());
        if (jdbcTemplate.update(sql.toString()) == 1) {
            if (enable) {
                return Message.success("启用成功");
            } else {
                return Message.success("禁用成功");
            }
        } else {
            return Message.warning("操作失败");
        }
    }

    /**实体*/
    private Class<T> getEntityClass() {
        this.entityClass = this.entityClass == null ? ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0])) : this.entityClass;
        return entityClass;
    }

    /**表名*/
    private String getTable() {
        try {
            if (tableName == null) {
                tableName = (String) FieldUtils.readDeclaredStaticField(entityClass, TABLE_FIELD_NAME);
            }
        } catch (Exception e) {
            logger.error("反射获取表名失败！"+ entityClass.getName(), e);
        }
        return tableName;
    }
}
