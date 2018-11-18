package us.betahouse.haetae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@ImportResource(locations = {"classpath:spring/validator.xml"})
@SpringBootApplication
public class HaetaeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaetaeWebApplication.class, args);
    }
}
