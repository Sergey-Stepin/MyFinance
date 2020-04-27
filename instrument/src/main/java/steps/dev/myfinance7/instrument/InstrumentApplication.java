package steps.dev.myfinance7.instrument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("steps.dev.myfinance7.common.model")
public class InstrumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentApplication.class, args);
	}

}
