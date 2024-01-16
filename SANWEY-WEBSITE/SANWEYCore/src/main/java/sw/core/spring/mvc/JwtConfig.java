package sw.core.spring.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author dwg
 * jwt配置类，用于设置JWT的密钥和过期时间等信息。
 * Date: 2023/11/28
 */
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String getSecret() {
        return secret;
    }

    public int getExpiration() {
        return expiration;
    }
}
