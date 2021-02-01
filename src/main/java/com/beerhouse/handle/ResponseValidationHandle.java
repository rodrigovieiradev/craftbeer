package com.beerhouse.handle;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.beerhouse.dto.ErrorFieldDto;
import com.beerhouse.dto.ErrorResponseDto;

@RestControllerAdvice
public class ResponseValidationHandle {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseDto handleValidation(HttpServletRequest request, MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldError =  exception.getBindingResult().getFieldErrors();
		List<ErrorFieldDto> errorFieldDtoList = new ArrayList<>();
		fieldError.forEach(e ->{
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			errorFieldDtoList.add(new ErrorFieldDto(e.getField(), mensagem));
			
		});
		return new ErrorResponseDto(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI(),
				                    request.getMethod(), errorFieldDtoList );
	
	}
}
