package com.codingdojo.pokemon.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokemon.models.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
	List<Pokemon> findAll();

}
