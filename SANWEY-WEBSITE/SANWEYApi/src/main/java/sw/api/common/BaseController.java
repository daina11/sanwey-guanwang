package sw.api.common;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import sw.common.util.SessionUtils;
import sw.common.sys.DateEditor;
import sw.core.web.SystemException;

import java.util.Date;

/**
 * 视图控制器-基类
 * @author wwh
 * @date 2021年1月26日
 */
public abstract class BaseController {

    //未登录拦截
    @ModelAttribute
    private void authUser() {
        if(SessionUtils.currentUser() == null) throw new SystemException(10001, "会话超时, 请重新登录");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));
    }
}