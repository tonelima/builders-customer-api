package com.builders.test648.customerapi.customer.builder;

import com.builders.test648.customerapi.customer.model.Customer;

public interface CustomerBuilderRequest<E, D> extends CustomerBuilder<E, D> {

	Customer copyToEntityIfNotNull(D dto, E entity);
}
