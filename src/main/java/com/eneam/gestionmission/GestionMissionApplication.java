package com.eneam.gestionmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


@Configuration
@SpringBootApplication
public class GestionMissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionMissionApplication.class, args);
	}

    @Bean
    Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }


}
