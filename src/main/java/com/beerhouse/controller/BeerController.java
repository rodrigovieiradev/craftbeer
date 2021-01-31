package com.beerhouse.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.service.BeerService;

@RestController
@RequestMapping("api/v1/beers")
public class BeerController {
	
	private BeerService beerService;
	
	@Autowired
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@PostMapping
	public ResponseEntity<BeerDto> createBeer(@RequestBody @Valid BeerDto beerDto, UriComponentsBuilder uriComponentBuilder) {
		 BeerDto beerCreatedDto = this.beerService.createBeer(beerDto);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(beerCreatedDto.getIdToString()).build().toUri();
		 return ResponseEntity.created(uri).body(beerCreatedDto);
	} 
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BeerDto> getAllBeer() {
		return this.beerService.getAllBeer();
	} 
	
	@GetMapping("/{beerId}")
	@ResponseStatus(HttpStatus.OK)
	public BeerDto getByBeer(@PathVariable Long beerId) {
		return this.beerService.getByBeer(beerId);
	} 
	
	@PutMapping("/{beerId}")
	@ResponseStatus(HttpStatus.OK)
	public BeerDto updateBeerComplete(@PathVariable Long beerId, 
			                         @RequestBody @Valid BeerDto beerDto) {
		return this.beerService.updateBeerComplete(beerId, beerDto);
	}
	
	@PatchMapping("/{beerId}")
	@ResponseStatus(HttpStatus.OK)
	public BeerDto alterBeer(@PathVariable Long beerId) {
		return this.beerService.alterBeer(beerId);
	} 
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable Long beerId) {
		 this.beerService.deleteBeer(beerId);
	} 

}
