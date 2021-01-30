package com.beerhouse.service;

import java.util.List;

import com.beerhouse.dto.BeerDto;

public interface BeerService {

	void createBeer(BeerDto beerDto);

	List<BeerDto> getAllBeer();

	BeerDto getByBeer(Long beerId);

	BeerDto updateBeerComplete(Long beerId, BeerDto beerDto);

	BeerDto alterBeer(Long beerId);

	void deleteBeer(Long beerId);




}
