package sw.core.redis;

import org.springframework.stereotype.Component;

/**
 * @author wwh
 * @date 2021/4/13
 * @email 644129971@qq.com
 */
@Component
public class RedisTools {
    private RedisConfig redisConfig;

    public RedisTools(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public RedisBuilder useDataBase(int dbIndex) {
        return RedisBuilder.build(redisConfig.getRedisTemplateByDb(dbIndex));
    }
}
