package com.palco.palcoprontolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PalcoProntoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalcoProntoListApplication.class, args);
	}

}
