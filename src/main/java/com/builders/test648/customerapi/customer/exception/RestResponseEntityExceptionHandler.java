package com.builders.test648.customerapi.customer.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {

		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		
		ResponseBodyExceptionDTO dto = new ResponseBodyExceptionDTO();
		dto.setStatus(HttpStatus.NOT_FOUND);
		dto.setError(ex.getMessage());
		dto.setTimestamp(new Date());
		dto.setPath(path);
		
		return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<Object> handleUncaught(RuntimeException ex, WebRequest request) {
		
		String path = StringUtils.delimitedListToStringArray(request.toString(), ";")[0];
		path = path.replace("ServletWebRequest: uri=", "");
		
		ResponseBodyExceptionDTO dto = new ResponseBodyExceptionDTO();
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		dto.setError(ex.getMessage());
		dto.setTimestamp(new Date());
		dto.setPath(path);
		
		return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	

}
