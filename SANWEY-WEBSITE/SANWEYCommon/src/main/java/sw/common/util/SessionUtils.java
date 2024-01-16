package sw.common.util;

import cn.hutool.core.codec.Base64;
import org.apache.commons.lang3.StringUtils;
import sw.core.spring.user.AuthUser;
import sw.core.web.SystemException;

/**
 * 会话管理
 * @author wwh
 * @date 2021/4/13
 * @email 644129971@qq.com
 */
public class SessionUtils {

    /**获取当前登录用户*/
    public static AuthUser currentUser(){
        try {
            String userJson = StringUtils.EMPTY;
            if (WebUtils.getHttpServletRequest().getHeaders("userInfo").hasMoreElements()){
                userJson = WebUtils.getHttpServletRequest().getHeaders("userInfo").nextElement();
            }
            if (StringUtils.isBlank(userJson)){
                throw new SystemException(20002, "未授权，获取用户信息失败");
            }
            return AuthUser.build(Base64.decodeStr(userJson));
        }catch (Exception e){
            return null;
        }
    }
}
