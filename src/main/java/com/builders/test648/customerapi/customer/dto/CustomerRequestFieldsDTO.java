package com.builders.test648.customerapi.customer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerRequestFieldsDTO {

	public static final String NAME = "name";
	public static final String BIRTH_DATE = "birthDate";
	public static final String AGE = "age";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String DOCUMENT_NUMBER = "documentNumber";
	public static final String DOCUMENT_TYPE = "documentType";
    
	private String name;
	private Integer age;
	private Date birthDate;
	private String address;
	private String phoneNumber;
	private String documentNumber;
	private String documentType;
}
