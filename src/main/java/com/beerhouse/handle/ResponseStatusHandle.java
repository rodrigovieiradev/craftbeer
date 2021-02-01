package com.beerhouse.handle;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.beerhouse.dto.ErrorResponseDto;
import com.beerhouse.expcetion.NotFoundException;

@RestControllerAdvice
public class ResponseStatusHandle {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorResponseDto handleValidation(HttpServletRequest request, NotFoundException exception) {
		return new ErrorResponseDto(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI(),
                request.getMethod());
	}
}
