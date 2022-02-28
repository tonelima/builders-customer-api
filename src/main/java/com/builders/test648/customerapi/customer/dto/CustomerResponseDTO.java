package com.builders.test648.customerapi.customer.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerResponseDTO  extends RepresentationModel<CustomerResponseDTO> {

	private Long id;
	private Date birthDate;
	private Integer age;
	private String name;
	private String address;
	private String phoneNumber;
	private String documentNumber;
	private String documentType;
}
