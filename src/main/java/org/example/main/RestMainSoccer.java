package org.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example")
@EnableJpaRepositories(basePackages = "org.example.db")
@EntityScan(basePackages = "org.example.db.entity")
public class RestMainSoccer {
    public static void main(String[] args) {
        SpringApplication.run(RestMainSoccer.class);
    }
}
