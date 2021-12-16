package com.example.springboot.repository;

//import com.alopezme.hotrodtester.configuration.CacheNames;
import com.example.springboot.data.Pokemon;
import com.example.springboot.repository.PokemonService;
import org.infinispan.client.hotrod.RemoteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EnableCaching
@CacheConfig(cacheNames = "pokemon-protostream")
@Service(value = "PokemonServiceProtoImpl")
public class PokemonServiceProtoImpl implements PokemonService {

    @Autowired
    @Qualifier("protostreamPokemonCache")
    private RemoteCache<Integer, Pokemon> pokemonCache;

    Logger logger = LoggerFactory.getLogger(PokemonServiceProtoImpl.class);

    @Override
    public Pokemon findById(int id){
        return pokemonCache.get(id);
    }

    @Override
    public Pokemon insert(int id, Pokemon pokemon){
        return pokemonCache.put(id, pokemon);
    }

    @Override
    public void delete(int id){
    	pokemonCache.remove(id);
    }

    @Override
    public boolean bulkRemove(Set<Integer> keys){
        return pokemonCache.keySet().removeAll(keys);
    }

    @Override
    public void deleteAll(){
    	pokemonCache.clear();
    }

    @Override
    public int getSize(){
        return pokemonCache.size();
    }

    @Override
    public String getKeys(){
        return pokemonCache.keySet().toString();
    }

    @Override
    public String getValues(){
        return pokemonCache.values().toString();
    }

    @Override
    public List<Pokemon> query(String query){
        // Method not supported for Java Serialization implementation
        return new ArrayList<Pokemon>();
    }

    @Override
    public List<Object[]> queryObject(String query){
        // Method not supported for Java Serialization implementation
        return new ArrayList<Object[]>();
    }
}