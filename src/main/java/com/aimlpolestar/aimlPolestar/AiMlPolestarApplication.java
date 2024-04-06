package com.aimlpolestar.aimlPolestar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AiMlPolestarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiMlPolestarApplication.class, args);
	}
	
	@RequestMapping(value = "/")
	public String hello() {
	   return "Welcome to AIML Vaiday Backend Universe";
	}

}
