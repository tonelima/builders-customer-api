package com.builders.test648.customerapi.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.builders.test648.customerapi.customer.builder.impl.CustomerBuilderRequestImpl;
import com.builders.test648.customerapi.customer.builder.impl.CustomerBuilderResponseImpl;
import com.builders.test648.customerapi.customer.dto.CustomerRequestDTO;
import com.builders.test648.customerapi.customer.dto.CustomerRequestFieldsDTO;
import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;
import com.builders.test648.customerapi.customer.exception.EntityNotFoundException;
import com.builders.test648.customerapi.customer.model.Customer;
import com.builders.test648.customerapi.customer.repository.CustomerRepository;
import com.builders.test648.customerapi.customer.service.CustomerService;
import com.builders.test648.customerapi.customer.specification.CustomerSpecification;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository repository;

	private CustomerBuilderResponseImpl responseBuilder;

	private CustomerBuilderRequestImpl requestBuilder;

	private final static String NO_CUSTOMERS_FOUND = "No customers found.";
	private final static String CUSTOMER_OF_ID_NOT_FOUND = "Customer of id %s not found.";

	public List<CustomerResponseDTO> findAll() {
		List<Customer> customers = repository.findAll();

		if (customers.isEmpty()) {
			throw new EntityNotFoundException(NO_CUSTOMERS_FOUND);
		}

		return responseBuilder.toDtoList(customers);
	}

	public CustomerResponseDTO findById(Long id) {
		Customer customer = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_OF_ID_NOT_FOUND, id)));

		return responseBuilder.toDto(customer);
	}

	public List<CustomerResponseDTO> findByFields(CustomerRequestFieldsDTO fieldsDto) {
		List<Customer> customers = repository.findAll(CustomerSpecification.byFields(fieldsDto));

		if (customers.isEmpty()) {
			throw new EntityNotFoundException(NO_CUSTOMERS_FOUND);
		}

		return responseBuilder.toDtoList(customers);
	}

	public Page<CustomerResponseDTO> findAllPaginated(Pageable pageable) {
		Page<Customer> pageCustomer = repository.findAll(pageable);

		if (pageCustomer.isEmpty()) {
			throw new EntityNotFoundException(NO_CUSTOMERS_FOUND);
		}

		List<CustomerResponseDTO> customers = pageCustomer.stream().map((c) -> responseBuilder.toDto(c))
				.collect(Collectors.toList());
		Page<CustomerResponseDTO> pageCustomerResponseDTO = new PageImpl<CustomerResponseDTO>(customers, pageable,
				pageCustomer.getTotalElements());
		return pageCustomerResponseDTO;
	}

	public CustomerResponseDTO create(CustomerRequestDTO requestDto) {
		Customer entity = requestBuilder.toEntity(requestDto);
		repository.save(entity);
		return responseBuilder.toDto(entity);
	}
	
	public CustomerResponseDTO update(CustomerRequestDTO requestDto) {
		Customer entityToUpdate = null;

		Optional<Customer> optional = repository.findById(requestDto.getId());
		entityToUpdate = optional.orElseThrow(
				() -> new EntityNotFoundException(String.format(CUSTOMER_OF_ID_NOT_FOUND, requestDto.getId())));

		requestBuilder.copyToEntityIfNotNull(requestDto, entityToUpdate);
		repository.save(entityToUpdate);
		return responseBuilder.toDto(entityToUpdate);
	}


}
