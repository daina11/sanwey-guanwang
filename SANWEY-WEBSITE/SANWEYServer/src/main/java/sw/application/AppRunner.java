package sw.application;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AppRunner implements CommandLineRunner {

    @Override
    public void run(String... args){
        log.info("<------------官网服务器启动成功------------>");
        log.info("<------------预加载程序开始执行------------>");
    }

}
