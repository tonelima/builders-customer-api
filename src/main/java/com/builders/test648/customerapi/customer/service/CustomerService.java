package com.builders.test648.customerapi.customer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.builders.test648.customerapi.customer.dto.CustomerRequestDTO;
import com.builders.test648.customerapi.customer.dto.CustomerRequestFieldsDTO;
import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;

public interface CustomerService {

	List<CustomerResponseDTO> findAll();
	
	CustomerResponseDTO findById(Long id);
	
	List<CustomerResponseDTO> findByFields(CustomerRequestFieldsDTO fieldsDto);
	
    Page<CustomerResponseDTO> findAllPaginated(Pageable pageable);

	CustomerResponseDTO create(CustomerRequestDTO requestDto);
	
	CustomerResponseDTO update(CustomerRequestDTO requestDto);
	
}
