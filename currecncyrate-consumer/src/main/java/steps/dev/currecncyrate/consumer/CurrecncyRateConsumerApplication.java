package steps.dev.currecncyrate.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CurrecncyRateConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrecncyRateConsumerApplication.class, args);
	}

}
