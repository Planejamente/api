package org.planejamente.planejamente;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(scheme = "bearer", name = "auth-api", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class PlanejamenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanejamenteApplication.class, args);
	}

}