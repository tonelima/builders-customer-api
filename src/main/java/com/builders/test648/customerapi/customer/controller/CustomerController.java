package com.builders.test648.customerapi.customer.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.builders.test648.customerapi.customer.dto.CustomerRequestDTO;
import com.builders.test648.customerapi.customer.dto.CustomerRequestFieldsDTO;
import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;
import com.builders.test648.customerapi.customer.hateoas.CustomerHateoasHelper;
import com.builders.test648.customerapi.customer.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	private CustomerHateoasHelper hateoasHelper;

	@ApiOperation(value = "Create a new customer")
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody @Valid CustomerRequestDTO dto) {
		service.create(dto);
	}

	@ApiOperation(value = "Update an existing customer")
	@PutMapping("/update")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody CustomerRequestDTO dto) {
		service.update(dto);
	}

	@ApiOperation(value = "List all customers")
	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> findAll() {

		List<CustomerResponseDTO> customers = service.findAll();
		hateoasHelper.addSelfLink(customers);
		return ResponseEntity.ok(customers);
	}
	
	@ApiOperation(value = "Find a specific customer by id")
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
		CustomerResponseDTO customer = service.findById(id);
		hateoasHelper.addToAllLink(customer, CustomerController.class);
		return ResponseEntity.ok(customer);
	}

	@ApiOperation(value = "List customers using pagination")
	@GetMapping("/pageable")
	public ResponseEntity<Page<CustomerResponseDTO>> findAllPaginated(Pageable pageable) {
		Page<CustomerResponseDTO> page = service.findAllPaginated(pageable);
		hateoasHelper.addSelfLink(page.getContent());
		return ResponseEntity.ok(page);
	}

	@ApiOperation(value = "Find customers by filters. If all filters are null, return all customers.")
	@GetMapping("/search")
	public ResponseEntity<List<CustomerResponseDTO>> findByFields(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer age, @RequestParam(required = false) Date birthDate,
			@RequestParam(required = false) String address, @RequestParam(required = false) String phoneNumber,
			@RequestParam(required = false) String documentNumber,
			@RequestParam(required = false) String documentType) {

		CustomerRequestFieldsDTO fieldsDto = new CustomerRequestFieldsDTO();
		fieldsDto.setName(name);
		fieldsDto.setAge(age);
		fieldsDto.setBirthDate(birthDate);
		fieldsDto.setAddress(address);
		fieldsDto.setPhoneNumber(phoneNumber);
		fieldsDto.setDocumentNumber(documentNumber);
		fieldsDto.setDocumentType(documentType);

		List<CustomerResponseDTO> customers = service.findByFields(fieldsDto);
		hateoasHelper.addSelfLink(customers);

		return ResponseEntity.ok(customers);
	}
}
