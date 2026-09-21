package br.com.nordestech.checkout.application;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.nordestech")
@EnableMongoRepositories(basePackages = "br.com.nordestech")
@EnableFeignClients(basePackages = "br.com.nordestech")
public class InfinitePayCheckoutApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory("./") // diretório base
                .ignoreIfMissing()
                .load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(InfinitePayCheckoutApplication.class, args);

    }

}
