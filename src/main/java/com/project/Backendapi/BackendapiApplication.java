package com.project.Backendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendapiApplication.class, args);
	}

}
