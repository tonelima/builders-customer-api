package com.builders.test648.customerapi.customer.builder.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.builders.test648.customerapi.customer.builder.CustomerBuilderResponse;
import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;
import com.builders.test648.customerapi.customer.model.Customer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerBuilderResponseImpl implements CustomerBuilderResponse<Customer, CustomerResponseDTO> {

	private ModelMapper modelMapperResponse;
	
	public CustomerResponseDTO toDto(Customer entity) {
		if (entity == null) {
			return null;
		}

		CustomerResponseDTO dto = modelMapperResponse.map(entity, CustomerResponseDTO.class);
		return dto;
	}

	public Customer toEntity(CustomerResponseDTO dto) {
		if (dto == null) {
			return null;
		}

		Customer entity = modelMapperResponse.map(dto,  Customer.class);
		return entity;
	}

	@Override
	public List<CustomerResponseDTO> toDtoList(List<Customer> entityList) {
		if (entityList == null) {
			return null;
		}

		return entityList.stream().map(e -> toDto(e)).collect(Collectors.toList());		
	}

	@Override
	public List<Customer> toEntityList(List<CustomerResponseDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}

		return dtoList.stream().map(e -> toEntity(e)).collect(Collectors.toList());	
	}

}
