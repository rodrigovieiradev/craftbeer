package com.beerhouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.exception.NotFoundException;
import com.beerhouse.model.Beer;
import com.beerhouse.service.impl.BeerServiceImpl;


@SpringBootTest(classes = { Application.class})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ComponentScan(basePackageClasses = Application.class)
public class BeerUnitTests {
	
	@Autowired
	private BeerServiceImpl beerServiceImpl;
	
	
	@Test
	public void shouldReturnNotNullValidateFindById() {
		Beer beer = new Beer();
		beer.setId(1l);
		beer.setIngredients("Malte");
		beer.setAlcoholContent("6,5%");
		beer.setName("Cerveja 1906 Reserva Especial");
		beer.setCategory("Helles Bock");
		beer.setPrice(BigDecimal.valueOf(20.00));
		Optional<Beer> beerOptional = Optional.ofNullable(beer);
		
		assertNotNull(this.beerServiceImpl.validateFindById(beerOptional));
	}
	
	@Test
	public void shouldNullValidateFindById() {
		Optional<Beer> beerOptional = Optional.empty();
		assertThrows(NotFoundException.class, () -> this.beerServiceImpl.validateFindById(beerOptional));
	}
	
	@Test
	public void shouldEqualsValidateSetBeerValues() {
		Beer beer = new Beer();
		beer.setId(1l);
		beer.setIngredients("Malte");
		beer.setAlcoholContent("6,5%");
		beer.setName("Cerveja 1906 Reserva Especial");
		beer.setCategory("Helles Bock");
		beer.setPrice(BigDecimal.valueOf(20.00));
		
		Beer beerNew = new Beer();
		beerNew.setId(2l);
		beerNew.setIngredients("Pilsen");
		beerNew.setName("Cerveja 1906 Black Coupage");
		beerNew.setCategory("Dunkel bock");
		beerNew.setPrice(BigDecimal.valueOf(30.00));
		
		beer.setBeerValues(beerNew);
		
		assertEquals(beer.getAlcoholContent(), beer.getAlcoholContent());
		assertEquals(beer.getName(), beerNew.getName());
		assertEquals(beer.getIngredients(), beerNew.getIngredients());
		assertEquals(beer.getCategory(), beerNew.getCategory());
		assertEquals(beer.getPrice(), beerNew.getPrice());
	}
	

	
}
