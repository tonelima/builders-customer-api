package com.builders.test648.customerapi.customer.builder.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.builders.test648.customerapi.customer.builder.CustomerBuilderRequest;
import com.builders.test648.customerapi.customer.dto.CustomerRequestDTO;
import com.builders.test648.customerapi.customer.model.Customer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerBuilderRequestImpl implements CustomerBuilderRequest<Customer, CustomerRequestDTO> {

	private ModelMapper modelMapperResponse;
	private ModelMapper modelMapperRequest;
	
	public CustomerRequestDTO toDto(Customer entity) {
		if (entity == null) {
			return null;
		}

		CustomerRequestDTO dto = modelMapperResponse.map(entity, CustomerRequestDTO.class);
		return dto;
	}

	public Customer toEntity(CustomerRequestDTO dto) {
		if (dto == null) {
			return null;
		}

		Customer entity = modelMapperResponse.map(dto, Customer.class);
		return entity;
	}

	@Override
	public List<CustomerRequestDTO> toDtoList(List<Customer> entityList) {
		if (entityList == null) {
			return null;
		}

		return entityList.stream().map(e -> toDto(e)).collect(Collectors.toList());
	}

	@Override
	public List<Customer> toEntityList(List<CustomerRequestDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}

		return dtoList.stream().map(e -> toEntity(e)).collect(Collectors.toList());
	}
	
	@Override
	public Customer copyToEntityIfNotNull(CustomerRequestDTO dto, Customer entity) {
		modelMapperRequest.map(dto, entity);
		return entity;
	}

}
