package com.beerhouse.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.expcetion.NotFoundException;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.BeerService;

@Service
public class BeerServiceImpl implements BeerService{
	
	private BeerRepository beerRepository;
	
	@Autowired
	public BeerServiceImpl(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public BeerDto createBeer(BeerDto beerDto) {
		Beer beer = beerDto.convertToEntity();
		return BeerDto.convertToDto(this.saveBeer(beer));
	}

	@Override
	public List<BeerDto> getAllBeer() {
		 return BeerDto.convertToListDto(this.findAll());
	}

	@Override
	public BeerDto getByBeer(Long beerId) {
		return BeerDto.convertToDto(this.finById(beerId)
				      .orElseThrow(() -> new NotFoundException("Beer not found")));
	}

	@Override
	public BeerDto updateBeerComplete(Long beerId, BeerDto beerDto) {
		 this.finById(beerId).orElseThrow(() -> new NotFoundException("Beer not found"));
		 beerDto.setId(beerId);
		 this.saveBeer(beerDto.convertToEntity());
		 return beerDto;
	}

	@Override
	public BeerDto alterBeer(Long beerId) {
		return null;
	}

	@Override
	public void deleteBeer(Long beerId) {
		Optional<Beer> beer = this.finById(beerId);
		if(beer.isPresent()) {
			this.deleteById(beerId);
		}else {
			throw new NotFoundException("Not found Beer:"+ beerId);
		}
	}
	
	@Transactional
	private List<Beer> findAll() {
		return beerRepository.findAll();
	}
	
	@Transactional
	private Beer saveBeer(Beer beer) {
		return beerRepository.save(beer);
	}
	
	@Transactional
	private Optional<Beer> finById(Long beerId) {
		return beerRepository.findById(beerId);
	}
	
	@Transactional
	private void deleteById(Long beerId) {
		 beerRepository.delete(beerId);
	}

}
