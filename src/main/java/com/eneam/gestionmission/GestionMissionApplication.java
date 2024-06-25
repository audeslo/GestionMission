package com.eneam.gestionmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;



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
