package tn.esprit.spring.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("PiDev")
                .description("Spring")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("E-spare")
                .email("E-Spare@esprit.tn")
                .url("https://www.facebook.com/e-spare");
        return contact;
    }

/*    @Bean
    public GroupedOpenApi productPublicApi() {
        return GroupedOpenApi.builder()
                .group("Only Contrat Management API")
                .pathsToMatch("/contrat/**")
                //.pathsToExclude("**")
                .build();
    }*/


}

