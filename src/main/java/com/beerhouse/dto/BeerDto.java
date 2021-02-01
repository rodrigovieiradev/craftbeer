package com.beerhouse.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.beerhouse.model.Beer;

public class BeerDto {

	private Long id;
	
	@NotNull(message = "Campo name é obrigatório.")
	@Size(max = 50, message = "Tamanho maximo excedido")
	private String name;
	
	@Size(max = 100, message = "Tamanho máximo excedido")
	private String ingredients;
	
	@Size(max = 50, message = "Tamanho máximo excedido")
	private String alcoholContent;
	
	@NotNull(message = "Campo price é obrigatório")
	@DecimalMin(value = "0")
	private BigDecimal price; 
	
	@NotNull(message = "Campo category é obrigatório.")
	private String category;
	
	public BeerDto(Beer beer) {
		this.id = beer.getId();
		this.name = beer.getName();
		this.ingredients = beer.getIngredients();
		this.alcoholContent = beer.getAlcoholContent();
		this.price = beer.getPrice();
		this.category = beer.getCategory();
	}
	
	public BeerDto() {
		
	}
	
	public Beer convertToEntity() {
		return new Beer(this.id, this.name, this.ingredients, 
				        this.alcoholContent,this.price, this.category);
	}
	
	public static List<BeerDto> convertToListDto(List<Beer> listBeer) {
		return listBeer.stream().map(BeerDto::new).collect(Collectors.toList());
	}
	
	public static BeerDto convertToDto(Beer beer) {
		return new BeerDto(beer);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getIdToString() {
		return id.toString();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
