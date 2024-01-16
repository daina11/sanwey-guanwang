package sw.biz.web;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import sw.biz.consul.ISanWeyDevopsConsul;
import sw.common.util.WebUtils;
import sw.core.constant.SystemStaticConfig;
import sw.core.web.Message;

/**
 * 异常信息处理
 * 记录到log4j和运维系统
 * @author wwh
 * @date 2021/10/18
 * @email 644129971@qq.com
 */
@Log4j2
@Component
public class MessageFroException {

    private static ISanWeyDevopsConsul sanWeyDevopsConsul;

    public MessageFroException(ISanWeyDevopsConsul sanWeyDevopsConsul) {
        this.sanWeyDevopsConsul = sanWeyDevopsConsul;
    }

    public static Message error(String message, Exception e){
        if (e != null && ArrayUtils.isNotEmpty(e.getStackTrace())) {
            log.error(message, e);
            StackTraceElement stackTraceElements =  e.getStackTrace()[0];
            String description = StringUtils.isBlank(e.getMessage()) ? e.getClass().getName() : e.getMessage();
            String exceptionApi = WebUtils.getHttpServletRequest().getServletPath();
            sanWeyDevopsConsul.exception(SystemStaticConfig.WEBSITE_NAME, description, stackTraceElements.getClassName(), stackTraceElements.getMethodName(), stackTraceElements.getLineNumber(), exceptionApi);
        }
        return Message.error(message);
    }
}
