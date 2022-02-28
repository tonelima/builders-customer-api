package com.builders.test648.customerapi.customer.builder;

import java.util.List;

public interface CustomerBuilder<E, D> {

	D toDto(E entity);
	E toEntity(D dto);

	List<D> toDtoList(List<E> entityList);
	List<E> toEntityList(List<D> dtoList);
}
