package com.estrellaticona.vetcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VetcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetcareApplication.class, args);
	}

}
