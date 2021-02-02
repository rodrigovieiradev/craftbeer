package com.beerhouse.exception;

public class InvalidArgumentException extends BeerHouseException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentException() {
		super();
	}
	
	public InvalidArgumentException(String format, Object... args) {
		super (String.format(format, args));
	}
}
