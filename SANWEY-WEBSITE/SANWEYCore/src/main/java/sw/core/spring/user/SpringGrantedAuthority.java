package sw.core.spring.user;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * oauth2 用户权限
 * @author wwh
 * @date 2020年12月16日
 */
public class SpringGrantedAuthority {

    private Map<String, Object> authorities = Maps.newHashMap();

    public Map<String, Object> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Map<String, Object> authorities) {
        this.authorities = authorities;
    }
}
