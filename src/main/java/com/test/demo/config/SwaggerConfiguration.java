package com.test.demo.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	 //@Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)
	          .apiInfo(
	        		  new ApiInfoBuilder()
	        		  .description("Gestion des utilisateurs documentation")
	        		  .title("Gestion des utilisateurs")
	        		  .build()
	        		  )
	          .groupName("REST API")
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("package com.test.demo"))              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
}
