package br.com.alex.proof.util.config;

import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.PathSelectors.*;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.configuration.SwaggerCommonConfiguration;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:api.properties", ignoreResourceNotFound = true),
					@PropertySource(value = "file:${user.dir}/api.properties", ignoreResourceNotFound = true)
					 })
@ComponentScan({ "br.com.alex.proof.util.controller", "springfox.documentation.swagger2.readers.parameter",
		"springfox.documentation.swagger2.mappers" })
@Import({  Swagger2DocumentationConfiguration.class, SpringfoxWebMvcConfiguration.class,SwaggerCommonConfiguration.class })
public class SwaggerConfig {

	@Value("${api.title}")
	private String appTitle;
	@Value("${api.description}")
	private String appDesc;
	@Value("${api.contact}")
	private String appContact;
	@Value("${api.version}")
	private String appVersion;

	@Bean
	public JacksonModuleRegistrar swagger2Module() {
		return new Swagger2JacksonModule();
	}

	/*
	 * Maybe token
	 * */
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("x-user-token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
     
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).globalOperationParameters(aParameters).select().paths(paths()).build();
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appTitle).description(appDesc).version(appVersion)
				.build();
	}

	private Predicate<String> paths() {
		return not(regex("/error"));
	}

}
