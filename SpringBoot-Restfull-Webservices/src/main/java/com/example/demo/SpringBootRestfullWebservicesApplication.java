package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
          info = @Info(
        		  title = "Spring Boot RestFull webservice Application",
        		  description = "Spring Boot RestFul webservice Application Demo",
        		  version = "v1.0.0",
        		  contact = @Contact(
        				  name = "Hari Prasad Kathi",
        				  email = "kattihariprasad@gmail.com",
        				  url = "@hariprasadkathi2589"
        				  )
        		  ),
          externalDocs = @ExternalDocumentation(
        		  description = "Spring Boot User Management Documentation",
        		  url = "http://harsha/user.management.html"
        		  )
          
		)
public class SpringBootRestfullWebservicesApplication {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfullWebservicesApplication.class, args);
	}

}
