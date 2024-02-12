package com.brandy.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.Collections;

@SpringBootApplication
public class CoursesApplication {
	public static void main(String[] args) {
		var app = new SpringApplication(CoursesApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "dev"));

		var ctx = app.run(args);
		MyFirstService myFirstService = ctx.getBean("myFirstService", MyFirstService.class);
		System.out.println(myFirstService.tellAStory());
		System.out.println(myFirstService.getOsName());
		System.out.println(myFirstService.getCustomProp("my.custom.name"));
		System.out.println(myFirstService.getCustomName());
		System.out.println(myFirstService.getCustomName2());
		System.out.println(myFirstService.getCustomName3());
	}
}
