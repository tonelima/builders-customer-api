package com.builders.test648.customerapi.customer.config;

import java.util.Date;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.builders.test648.customerapi.customer.dto.CustomerResponseDTO;
import com.builders.test648.customerapi.customer.model.Customer;
import com.builders.test648.customerapi.utils.DateUtils;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapperResponse() {
		ModelMapper modelMapper = new ModelMapper();	

		Converter<Date, Integer> ageConverter = ctx -> DateUtils.getAge(ctx.getSource());
		
		modelMapper.createTypeMap(Customer.class, CustomerResponseDTO.class)
			.addMappings(mapper -> mapper.using(ageConverter)
					.map(Customer::getBirthDate, CustomerResponseDTO::setAge));
		
		return modelMapper;
	}
	
	@Bean
	public ModelMapper modelMapperRequest() {
		ModelMapper modelMapper = new ModelMapper();	
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

}
