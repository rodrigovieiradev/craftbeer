package com.beerhouse.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorResponseDto {

	private HttpStatus status;
	private String timestamp;
	private String message;
	private String path;
	private String method;
	@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
	private List<ErrorFieldDto> errors;

	public ErrorResponseDto(HttpStatus status, String message, String path, String method, List<ErrorFieldDto> errors) {
		this.status = status;
		this.timestamp = LocalDateTime.now().toString();
		this.message = this.formatMessage(message);
		this.path = path;
		this.method = method;
		this.errors = errors;
	}

	public ErrorResponseDto(HttpStatus status, String message, String path, String method) {
		this.status = status;
		this.timestamp = LocalDateTime.now().toString();
		this.message = message;
		this.path = path;
		this.method = method;
	}

	public String formatMessage(String message) {
		if(!message.isEmpty()) {
		  return message.contains(":") ? message.substring(0, message.indexOf(':')): message;			
		}
		return "";
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<ErrorFieldDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorFieldDto> errors) {
		this.errors = errors;
	}
	
}
