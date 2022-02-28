package com.builders.test648.customerapi.customer.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("com.builders.test648")
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.builders.test648.customerapi.customer.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT());
    }
    
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("Builders", "https://platformbuilders.io/", "testmail@platformbuilders.io");
        return new ApiInfoBuilder()
                .title("Customer API")
                .description("API to manage customers")
                .version("1.0")
                .contact(contact)
                .build();
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {/**
			 * 
			 */
			private static final long serialVersionUID = -4633010210164524502L;

		{
            add(new ResponseMessageBuilder()
                .code(200)
                .message("OK")
                .build());
            add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPOST()
    {
        return new ArrayList<ResponseMessage>() {/**
			 * 
			 */
			private static final long serialVersionUID = -3788003176501014838L;

		{
            add(new ResponseMessageBuilder()
                .code(201)
                .message("Created")
                .build());
            add(new ResponseMessageBuilder()
                .code(400)
                .message("Bad Request")
                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPUT()
    {
        return new ArrayList<ResponseMessage>() {/**
			 * 
			 */
			private static final long serialVersionUID = -2260938882344608503L;

		{
            add(new ResponseMessageBuilder()
                .code(200)
                .message("OK")
                .build());
            add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .build());
        }};
    }
}