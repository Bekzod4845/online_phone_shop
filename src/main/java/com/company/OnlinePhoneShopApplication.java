package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.company.repository")
@EntityScan(basePackages = { "com.company.entity" })
public class OnlinePhoneShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlinePhoneShopApplication.class, args);
    }
}
