package com.w2w.What2Watch;

import com.w2w.What2Watch.models.myHelloWorldCollection;
import com.w2w.What2Watch.repositories.myHelloWorldCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class What2WatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(What2WatchApplication.class, args);
	}

	@Autowired
	private myHelloWorldCollectionRepository myHelloWorldCollectionRepository;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

		return String.format("Hello World !");
	}
}
