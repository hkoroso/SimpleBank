package com.simple_bank.loans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="AuditorAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservice",
				version = "1.0",
				description = "Loans Microservice",
				contact = @io.swagger.v3.oas.annotations.info.Contact(
						name = "Gano Koroso",
						email = "gkoroso@miu.edu",
						url = "https://www.miu.edu/"

		),
				license = @io.swagger.v3.oas.annotations.info.License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0.html"
				)
		)
)

public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
