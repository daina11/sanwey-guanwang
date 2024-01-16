package sw.core.redis;

import com.sun.istack.NotNull;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author wwh
 * @date 2021年1月14日
 */
public class RedisBuilder {

    private RedisTemplate redisTemplate;

    private RedisBuilder() {
    }

    private RedisBuilder(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static RedisBuilder build(RedisTemplate redisTemplate){
        return new RedisBuilder(redisTemplate);
    }

    /**
     * 插入
     */
    public void put(@NotNull String key, @NotNull Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存、缓存失效时间
     */
    public void put(@NotNull String key, @NotNull Object value, @NotNull long time, @NotNull TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 取出
     */
    public Object takeOut(@NotNull String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 验证Key存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除
     */
    public void delete(String... key) {
        redisTemplate.delete(Arrays.asList(key));
    }
}
