package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HWAprojectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(HWAprojectApplication.class, args);
		
		String timeStamp = beanBag.getBean("localTime", String.class);
		
		System.out.println(timeStamp);
	}
}
