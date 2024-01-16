package sw.biz.consul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 运维系统-异常日志
 * @author wwh
 * @date 2021/5/27
 * @email 644129971@qq.com
 */
@FeignClient(name = "sanWeyDevops")
public interface ISanWeyDevopsConsul {

    @PostMapping(value = "/devops/consul/systemlog/exception")
    void exception(@RequestParam String system, @RequestParam String description, @RequestParam String exceptionClass, @RequestParam String exceptionMethod, @RequestParam Integer exceptionLine, @RequestParam String exceptionApi);

    @PostMapping(value = "/devops/consul/systemlog/execute")
    void execute(@RequestParam String system, @RequestParam String description, @RequestParam String exceptionClass, @RequestParam String exceptionMethod, @RequestParam Integer exceptionLine, @RequestParam String exceptionApi);
}