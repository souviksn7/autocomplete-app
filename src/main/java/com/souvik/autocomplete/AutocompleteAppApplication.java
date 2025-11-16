package com.souvik.autocomplete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot Autocomplete application.
 * Boots the application context and starts the embedded server.
 */
@SpringBootApplication
public class AutocompleteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutocompleteAppApplication.class, args);
	}

}
