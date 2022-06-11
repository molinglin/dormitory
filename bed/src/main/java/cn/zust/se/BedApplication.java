package cn.zust.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BedApplication {
    public static void main(String[] args) {
        SpringApplication.run(BedApplication.class,args);
    }
}
