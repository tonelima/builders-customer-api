package com.builders.test648.customerapi.customer.test;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.builders.test648.customerapi.customer.databaseservice.DatabaseService;
import com.builders.test648.customerapi.customer.model.Customer;
import com.builders.test648.customerapi.customer.repository.CustomerRepository;
import com.builders.test648.customerapi.customer.utils.JsonLoader;
import com.builders.test648.customerapi.utils.DateUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/api-test.properties")
public class CustomerTest {

	@LocalServerPort
	private int port;

    @Autowired
    private CustomerRepository repository;
    
    @Autowired
    private DatabaseService database;

	private String jsonCustomerCreate;
	private String jsonCustomerUpdateFull;
	private String jsonCustomerUpdatePartial;

	@Before
	public void setup() {
		RestAssured.port = port;
		RestAssured.basePath = "/customers";

		jsonCustomerCreate = JsonLoader.getJsonFromFilename("/json/customer-create.json");
		jsonCustomerUpdateFull = JsonLoader.getJsonFromFilename("/json/customer-update-full.json");
		jsonCustomerUpdatePartial = JsonLoader.getJsonFromFilename("/json/customer-update-partial.json");
		
		database.cleanDatabase();
		initData();
	}

	@Test
	public void whenCreateCustomer_thenStatus201() {
		given().body(jsonCustomerCreate)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when().post("/create")
			.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void whenUpdateFullCustomer_thenStatus200() {
		given().body(jsonCustomerUpdateFull)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when().put("/update")
			.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void whenUpdatePartialCustomer_thenStatus200() {
		given().body(jsonCustomerUpdatePartial)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when().put("/update")
			.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void whenGetAllCustomers_thenStatus200() {
		given()
			.when().get("")
			.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void whenGetAllCustomersWithPagination_thenStatus200ETotalElements2() {
		given()
			.when().get("/pageable?page=0&size=2")
			.then().statusCode(HttpStatus.OK.value())
			.body("totalElements", Is.is(2));
	}
	
	@Test
	public void whenGetCustomersByFilterAgeIs30_thenStatus200() {
		given()
			.when().get("/search?age=30")
			.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void whenGetCustomersByFilterAgeIs31_thenStatus404() {
		given()
			.when().get("/search?age=31")
			.then().statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	void initData() {
        Customer customer1 = Customer.builder()
        			.name("Marco Antonio")
        			.birthDate(DateUtils.addYears(new Date(), -30))
        			.address("Av Principal, 345")
        			.phoneNumber("85987456321")
        			.documentNumber("03256987452")
        			.documentType("CPF").build();
        repository.save(customer1);
        
        Customer customer2 = Customer.builder()
    			.name("Adamastor")
    			.birthDate(DateUtils.addYears(new Date(), -40))
    			.address("Av das Americas, 1001")
    			.phoneNumber("85988997541")
    			.documentNumber("12547852603")
    			.documentType("CPF").build();
        repository.save(customer2);
	}
}
