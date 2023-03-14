package dev.be.loansystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoanSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanSystemApplication.class, args);
    }

}
