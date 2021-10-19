package com.rbctest.api.stocksapi.v1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig  {

	  @Bean
	    public OpenAPI openAPI(){
	        return new OpenAPI().info(apiInfo()).components(new Components());
	    }

	private Info apiInfo() {
	return new Info().title("Rbc Assessment API").description("This is a RESTful API in which stock info can be uploaded and retrived");
	}

}