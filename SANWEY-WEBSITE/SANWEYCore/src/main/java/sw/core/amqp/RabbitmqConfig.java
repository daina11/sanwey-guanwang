package sw.core.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwh
 * @date 2021/4/14
 * @email 644129971@qq.com
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue email(){
        return new Queue("email");
    }

    @Bean
    public Queue smsCode(){
        return new Queue("smsCode");
    }

}
