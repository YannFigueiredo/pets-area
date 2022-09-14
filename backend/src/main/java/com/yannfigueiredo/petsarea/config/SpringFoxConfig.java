package com.yannfigueiredo.petsarea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yannfigueiredo.petsarea.controllers"))
				.paths(PathSelectors.any())
				.build()        
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Pets Area API")
	            .description("API com um CRUD de cadastro de animais de estimação dos usuários.\n\n"
	            		+ "***Autenticação em desenvolvimento***")
	            .version("1.0.0")
	            .contact(new Contact("Yann Figueiredo", "https://www.linkedin.com/in/yannfigueiredo/", "yann.fabricio@hotmail.com"))
	            .build();
	}
}
