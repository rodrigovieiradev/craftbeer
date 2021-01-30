package com.beerhouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beerhouse.model.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long>  {

	Optional<Beer> findById(Long beerId);
	
	
}
