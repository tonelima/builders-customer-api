package com.builders.test648.customerapi.customer.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerRequestDTO {
	
	private Long id;
	
	@ApiModelProperty(required = true)
	@NotBlank
	private String name;

    @JsonFormat(locale = "br", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Brazil/East")
    private Date birthDate;
	
	private String address;
	
	private String phoneNumber;
	
	private String documentNumber;
	
	private String documentType;
}
