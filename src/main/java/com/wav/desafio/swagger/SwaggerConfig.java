package com.wav.desafio.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("DB Desafio Votação")
                        .description("API responsável pela contagem de votos dos associados em determinada pauta")
                        .version("1.0")
                        .contact(new Contact().name("Willian Valer")
                                .email("willianvaler@gmail.com")))
                .externalDocs(new ExternalDocumentation().description("Repositório da API")
                        .url("https://github.com/willianvaler/desafio-votacao"));
    }
}
