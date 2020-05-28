package steps.dev.myfinance7.operation.generator;

import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import steps.dev.myfinance7.operation.generator.service.OperationGeneratorService;
import steps.dev.myfinance7.operation.generator.service.RandomOperationGenerator;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OperationGeneratorApplication implements CommandLineRunner {
    
    @Autowired
    private OperationGeneratorService operationGeneratorService;
    
    @Autowired
    private RandomOperationGenerator operationGenerator;

    public static void main(String[] args) {
        SpringApplication.run(OperationGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        IntStream.range(0, 10)
                .forEach(this::sendRandomOperation);
    }
    
    private void sendRandomOperation(int i){
        operationGeneratorService.send(operationGenerator.generate());
    }

}
