package tech.wvs.movieflix.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.setUrl("https://github.com/wallacevilela7/MovieFlix");
        contact.name("Wallace Vilela");
        contact.email("wallace.vilela9@gmail.com");

        Info info = new Info();

        info.title("Movieflix API");
        info.description("Aplicacao para gerenciamento de catalogo de filmes");
        info.version("v1");
        info.contact(contact);


        return new OpenAPI().info(info);
    }
}
