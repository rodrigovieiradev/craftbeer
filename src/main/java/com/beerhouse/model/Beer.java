package com.beerhouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name="beer")
public class Beer implements Serializable   {
	
	private static final long serialVersionUID = 1211693181517469171L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "ingredients", nullable = true)
	private String ingredients;
	
	@Column(name = "alcoholContent", nullable = true)
	private String alcoholContent;
	
	@Column(name = "price", nullable = false,
	        precision = 21, scale = 2)
	private BigDecimal price; 
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "registrationDate", nullable = false)
	private LocalDateTime registrationDate = LocalDateTime.now();

	public Beer(Long id, String name, String ingredients, String alcoholContent, 
			    BigDecimal price, String category) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
	}
	
	public Beer() {
	}

	public Long getId() {
		return id;
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

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public void setBeerValues(Beer beer) {
		this.name = (String) this.verifyFields(beer.name, this.name);
		this.ingredients = (String) verifyFields(beer.ingredients, this.ingredients);
		this.price = (BigDecimal) this.verifyFields(beer.price, this.price);
		this.category = (String) this.verifyFields(beer.category, this.category);
		this.alcoholContent = (String) this.verifyFields(beer.getAlcoholContent(), this.getAlcoholContent());
	}
	
	private Object verifyFields(Object newField, Object field) {
	   return newField == null ? field : newField;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}




	
	
}
