package com.beerhouse.expcetion;


public class NotFoundException extends BeerHouseException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String format, Object... args) {
		super(format, args);
	}
}
