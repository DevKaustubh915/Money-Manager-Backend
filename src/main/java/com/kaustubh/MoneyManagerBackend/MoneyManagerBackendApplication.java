package com.kaustubh.MoneyManagerBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoneyManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManagerBackendApplication.class, args);
	}

}
