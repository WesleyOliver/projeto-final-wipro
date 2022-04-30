package br.edu.gama.gamaster;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Gamaster API",
                description = "Api para Gerenciar contas e movimentações",
                version = "0.0.1",
                contact = @Contact(name = "Equipe Gamaster", url = "gamaster.com.br", email = "desenvolvimento@gamaster.com.br")
        ))
public class GamasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamasterApplication.class, args);
    }
}


