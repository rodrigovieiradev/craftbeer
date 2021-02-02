package com.beerhouse.exception;


public class BeerHouseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BeerHouseException() {
		super();
	}
	
	public BeerHouseException(String format, Object... args) {
		super (String.format(format, args));
	}
}
