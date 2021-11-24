package com.example.springboot.controller;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private final RemoteCacheManager cacheManager;

	@Autowired
	public HelloController(RemoteCacheManager cacheManager) {
		  this.cacheManager = cacheManager;
	}
	
	@GetMapping("/")
	public String index() {

		return "Greetings from Spring Boot!";
	}

	@GetMapping("/infinispan")
    public void addInfinispan() {
		cacheManager.getCache("default").put("hola", "tin");
		System.out.println("Received value from cache: " + cacheManager.getCache("default").get("hola"));
    }

}
