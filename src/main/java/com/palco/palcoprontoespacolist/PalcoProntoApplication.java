package com.palco.palcoprontoespacolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PalcoProntoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalcoProntoApplication.class, args);
	}

}
