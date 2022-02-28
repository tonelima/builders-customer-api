package com.builders.test648.customerapi.customer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseBodyExceptionDTO {

	private Date timestamp;
	private HttpStatus status;
	private String error;
	private String path;
}
