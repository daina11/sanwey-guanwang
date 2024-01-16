package sw.core.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis多数据库连接配置
 * @author wwh
 * @date 2021年1月14日
 */
@Component
@ConfigurationProperties(prefix = "redis.config")
public class RedisConfig {

    /**redis地址*/
    private String host;

    /**redis端口号*/
    private int port;

    /**redis密码*/
    private String password;

    /**多个数据库集合*/
    private List<Integer> dbList;

    /**默认数据库*/
    private int defaultDB;

    /**RedisTemplate实例*/
    private static Map<Integer, RedisTemplate<String, Object>> redisTemplateMap = new HashMap<>();

    /** 指定数据库进行切换 */
    public RedisTemplate<String, Object> getRedisTemplateByDb(int db) {
        return redisTemplateMap.get(db);
    }

    /** 使用默认数据库 */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplateMap.get(defaultDB);
    }

    /** 初始化连接池 */
    @PostConstruct
    public void initRedisTemplate() {
        defaultDB = dbList.get(0);//设置默认数据库
        for (Integer db : dbList) {
            redisTemplateMap.put(db, redisTemplate(db));//存储多个RedisTemplate实例
        }
    }

    /**RedisTemplate模板*/
    private RedisTemplate<String, Object> redisTemplate(int db) {
        //为了开发方便，一般直接使用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnection(db)); //设置连接
        template.afterPropertiesSet();
        return template;
    }

    /**定义redis连接*/
    private LettuceConnectionFactory redisConnection(int db) {
        RedisStandaloneConfiguration server = getRedisStandaloneConfiguration(db);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(server);
        factory.afterPropertiesSet(); //刷新配置
        return factory;
    }

    /** 基本配置 */
    private RedisStandaloneConfiguration getRedisStandaloneConfiguration(int db) {
        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
        server.setHostName(host); // 指定地址
        server.setDatabase(db); // 指定数据库
        server.setPort(port); //指定端口
        server.setPassword(password); //指定密码
        return server;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getDbList() {
        return dbList;
    }

    public void setDbList(List<Integer> dbList) {
        this.dbList = dbList;
    }

    public int getDefaultDB() {
        return defaultDB;
    }

    public void setDefaultDB(int defaultDB) {
        this.defaultDB = defaultDB;
    }
}
