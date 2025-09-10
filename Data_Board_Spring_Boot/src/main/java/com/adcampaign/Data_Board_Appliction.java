package com.adcampaign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// 核心注解：标记这是一个Spring Boot应用的主配置类
@SpringBootApplication
@RestController
public class Data_Board_Appliction {
	public static void main(String[] args) {
		SpringApplication.run(Data_Board_Appliction.class, args);
	}
}