package sw.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring管理类
 * @author wwh
 * @date 2021年1月14日
 */
public final class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) SpringUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String var1){
        return applicationContext.getBean(var1);
    }
}
