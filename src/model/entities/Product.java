package model.entities;

import model.exceptions.DomainException;

public class Product {

	private String name;
	private Double price;
	
	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) throws DomainException {
		if (price <= 0 ) {
			throw new DomainException("Price must be greater than zero");
		}
		this.price = price;
	}	

}
