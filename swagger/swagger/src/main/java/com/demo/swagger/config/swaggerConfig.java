package com.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration

public class swaggerConfig {

	/*
	 * @Bean public Docket swaggerConfiguration() { return new
	 * Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(
	 * PathSelectors.ant("/api/*"))
	 * .apis(RequestHandlerSelectors.basePackage("com.demo.swagger")).build(); }
	 */

	@Bean
	public Docket SwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.demo.swagger")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("User Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Book Rest API")
				.termsOfServiceUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
				.license("Java Swagger Task").version("1.0").build();
	}

}
