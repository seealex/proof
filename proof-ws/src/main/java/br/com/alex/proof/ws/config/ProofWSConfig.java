package br.com.alex.proof.ws.config;

import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import br.com.alex.proof.util.config.SwaggerConfig;

@SpringBootApplication
@EnableAsync
@EnableMongoRepositories(basePackages = "br.com.alex.proof.ws.repository")
@PropertySources({ @PropertySource(value = "classpath:api-config.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${user.dir}/api-config.properties", ignoreResourceNotFound = true) })
@ComponentScan({ "br.com.alex.proof.ws.controller","br.com.alex.proof.ws.repository", "br.com.alex.proof.ws.service" })
@Import({ SwaggerConfig.class })
public class ProofWSConfig{
	
	public static void main(String[] args) {
		SpringApplication.run(ProofWSConfig.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("Brazil/East"));
	}
}
