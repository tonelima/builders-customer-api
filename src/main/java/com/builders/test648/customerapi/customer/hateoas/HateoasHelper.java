package com.builders.test648.customerapi.customer.hateoas;

import java.util.List;

public interface HateoasHelper<T> {
	
	void addToAllLink(T entity, Class<?> typeClass);
	void addSelfLink(List<T> entities); 
}
