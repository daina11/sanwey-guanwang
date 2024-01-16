package sw.common.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.stereotype.Component;
import sw.core.redis.RedisBuilder;
import sw.core.redis.RedisTools;

/**
 * redis工具类
 * @author wwh
 * @date 2021年1月14日
 */
@Component
public class RedisUtils {

    public static RedisBuilder useDataBase(int dbIndex) {
        RedisTools redisTools = SpringUtil.getBean("redisTools", RedisTools.class);
        return redisTools.useDataBase(dbIndex);
    }

}
