package sw.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 系统启动入口
 * @author wwh
 */
@EnableCaching
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "sw.biz.consul")
@EntityScan(basePackages = {"sw.biz.*.entity", "sw.biz.*.*.entity"})
@EnableJpaRepositories(basePackages = "sw.biz.*.repository")
@SpringBootApplication(scanBasePackages = "sw.*")
public class SanWeyWmsMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanWeyWmsMainApplication.class, args);
	}

}
