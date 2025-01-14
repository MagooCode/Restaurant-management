package az.springlesson.fooddelivery.msuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUserServiceApplication.class, args);
    }

}
