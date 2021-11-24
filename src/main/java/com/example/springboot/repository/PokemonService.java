package com.example.springboot.repository;

import com.example.springboot.data.Pokemon;

import java.util.List;
import java.util.Set;

public interface PokemonService {

    public Pokemon findById(int id);

    public Pokemon insert(int id, Pokemon pokemon);

    public void delete(int id);

    public boolean bulkRemove(Set<Integer> keys);

    public void deleteAll();

    public int getSize();

    public String getKeys();

    public String getValues();

    public List<Pokemon> query(String query);

    public List<Object[]> queryObject(String query);

}