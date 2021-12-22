package com.springboot_jpa_mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootJpaMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaMallApplication.class, args);
	}

}
