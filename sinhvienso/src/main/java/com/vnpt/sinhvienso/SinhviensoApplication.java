package com.vnpt.sinhvienso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {
//		org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class,
//		org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class
//})

public class SinhviensoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinhviensoApplication.class, args);
	}

}
