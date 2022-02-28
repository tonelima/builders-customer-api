package com.builders.test648.customerapi.customer.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.builders.test648.customerapi.customer.controller.CustomerController;
import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;

@Component
public class CustomerHateoasHelper implements HateoasHelper<CustomerResponseDTO> {

	@Override
	public void addToAllLink(CustomerResponseDTO entity, Class<?> typeClass) {
		Link link = linkTo(typeClass).withSelfRel();
		entity.add(link);		
	}

	@Override
	public void addSelfLink(List<CustomerResponseDTO> entities) {
		for (CustomerResponseDTO customer : entities) {
			Link selfLink = linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel();
			customer.add(selfLink);
		}
	}

}
