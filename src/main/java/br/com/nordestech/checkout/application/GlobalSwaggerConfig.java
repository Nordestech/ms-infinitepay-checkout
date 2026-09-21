package br.com.nordestech.checkout.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("InfinitePay Checkout")
                        .version("1.0.0")
                        .description("Microservice API para pagamentos pelo InfinitePay"));
    }

}