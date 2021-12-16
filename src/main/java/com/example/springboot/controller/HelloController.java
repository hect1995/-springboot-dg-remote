package com.example.springboot.controller;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.data.Pokemon;
import com.example.springboot.repository.PokemonService;
import com.example.springboot.repository.PokemonServiceProtoImpl;

@RestController
@RequestMapping("proto")
public class HelloController {
	
	//private final RemoteCacheManager cacheManager;
    
    @Autowired
    @Qualifier("PokemonServiceProtoImpl")
    private PokemonService pokemonRepository;
    
	//@Autowired
	//public HelloController(RemoteCacheManager cacheManager) {
	//	  this.cacheManager = cacheManager;
	//}
    Logger logger = LoggerFactory.getLogger(HelloController.class);

	@GetMapping("/")
	public String index() {

		return "Greetings from Spring Boot!";
	}

	@GetMapping("/infinispan/{id}")
    public void addInfinispan(
    		@PathVariable(value = "id") int id) {
        Pokemon pokemon = new Pokemon(id,"Charizard" ,"fire");
        pokemonRepository.insert(id,pokemon);
		//System.out.println("Received value from cache: " + cacheManager.getCache("pokemon").get("hola"));
    }

}
