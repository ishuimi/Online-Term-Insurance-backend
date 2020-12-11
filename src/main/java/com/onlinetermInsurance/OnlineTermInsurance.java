package com.onlinetermInsurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class OnlineTermInsurance {

	private static final Logger logger = LoggerFactory.getLogger(OnlineTermInsurance.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(OnlineTermInsurance.class, args);
		logger.debug("This is a debug message");
		logger.info("This is a info message");
		logger.warn("This is a warn message");
		logger.error("This is a error message");
		System.out.println("hello");
		
	}
}
