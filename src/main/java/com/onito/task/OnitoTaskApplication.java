package com.onito.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.onito.task.entity.Movies;

@EnableWebMvc
@ComponentScan("com.*.*")
@SpringBootApplication

public class OnitoTaskApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(OnitoTaskApplication.class, args);
	}

	 
}
