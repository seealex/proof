package br.com.alex.proofuser.ws.config;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import br.com.alex.proof.util.config.SwaggerConfig;

@SpringBootApplication
@EnableAsync
@EnableMongoRepositories(basePackages = "br.com.alex.proofuser.ws.repository")
@PropertySources({ @PropertySource(value = "classpath:api-config.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${user.dir}/api-config.properties", ignoreResourceNotFound = true) })
@ComponentScan({ "br.com.alex.proofuser.ws.controller","br.com.alex.proofuser.ws.repository", "br.com.alex.proofuser.ws.service" })
@Import({ SwaggerConfig.class })
public class ProofUserWSConfig {

	public static void main(String[] args) {
		SpringApplication.run(ProofUserWSConfig.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("Brazil/East"));
	}
	
}
