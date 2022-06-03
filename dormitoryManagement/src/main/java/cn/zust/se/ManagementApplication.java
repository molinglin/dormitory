package cn.zust.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class,args);
    }
}
