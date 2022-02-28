package com.builders.test648.customerapi.customer.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.builders.test648.customerapi.customer.dto.CustomerRequestFieldsDTO;
import com.builders.test648.customerapi.customer.model.Customer;
import com.builders.test648.customerapi.utils.DateUtils;

public class CustomerSpecification {

	public static Specification<Customer> byFields(CustomerRequestFieldsDTO fields) {

		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();

			addPredicateLike(predicates, fields.getName(), CustomerRequestFieldsDTO.NAME, root, query, builder);
			addPredicate(predicates, fields.getAddress(), CustomerRequestFieldsDTO.ADDRESS, root, query, builder);
			addPredicate(predicates, fields.getPhoneNumber(), CustomerRequestFieldsDTO.PHONE_NUMBER, root, query,
					builder);
			addPredicate(predicates, fields.getBirthDate(), CustomerRequestFieldsDTO.BIRTH_DATE, root, query, builder);

			addPredicateOfAge(predicates, fields.getAge(), root, query, builder);

			addPredicate(predicates, fields.getDocumentNumber(), CustomerRequestFieldsDTO.DOCUMENT_NUMBER, root, query,
					builder);
			addPredicate(predicates, fields.getDocumentType(), CustomerRequestFieldsDTO.DOCUMENT_TYPE, root, query,
					builder);

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};

	}

	private static void addPredicate(List<Predicate> predicates, String value, String fieldName, Root<Customer> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		Optional.ofNullable(value).ifPresent(v -> predicates.add(builder.like(root.get(fieldName), v.trim())));
	}

	private static void addPredicate(List<Predicate> predicates, Date value, String fieldName, Root<Customer> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		Optional.ofNullable(value).ifPresent(v -> predicates.add(builder.equal(root.<Date>get(fieldName), v)));
	}

	private static void addPredicateOfAge(List<Predicate> predicates, Integer age, Root<Customer> root,
			CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (age != null) {
			Date beginDate = DateUtils.birthPeriodBeginDate(age);
			Date endDate = DateUtils.birthPeriodEndDate(age);

			predicates.add(builder.between(root.<Date>get(CustomerRequestFieldsDTO.BIRTH_DATE), beginDate, endDate));
		}
	}

	private static void addPredicateLike(List<Predicate> predicates, String value, String fieldName,
			Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Optional.ofNullable(value)
				.ifPresent(v -> predicates.add(builder.like(root.get(fieldName), String.format("%s%", v.trim()))));
	}

}
