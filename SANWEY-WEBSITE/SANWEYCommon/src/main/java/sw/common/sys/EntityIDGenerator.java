package sw.common.sys;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import sw.common.util.RandomUtils;

import java.io.Serializable;
import java.util.Properties;

/**
 * 数据库ID主键生成策略
 * @author wwh
 * @date 2020年12月17日
 */
public class EntityIDGenerator implements IdentifierGenerator, Configurable {

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {}

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return RandomUtils.getUUID();
    }

}
