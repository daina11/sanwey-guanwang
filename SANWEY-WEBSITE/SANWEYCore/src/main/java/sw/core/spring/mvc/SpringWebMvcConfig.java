package sw.core.spring.mvc;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sw.core.constant.SystemStaticConfig;
import sw.core.jwt.JwtInterceptor;

import java.util.List;

@Log4j2
@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;

    @Autowired
    public SpringWebMvcConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加JwtInterceptor，并指定拦截路径
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/web/admin/**")/// 拦截以"/api/"开头的路径
                .excludePathPatterns("/web/admin/login", "/web/admin/register", "/web/admin/logout","/web/admin/carousel/upload");
    }

    /** 服务器资源配置 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + SystemStaticConfig.FILE_UPLOAD_SAVE_PATH, "file:E:\\sanwey\\upload\\");
    }

    /** 请求响应json格式 */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(converter);
    }
}
