package br.com.alex.proof.util.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

import io.swagger.models.Swagger;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

@RestController
@RequestMapping("swagger")
public class CustomSwaggerController {

	@Autowired
	private DocumentationCache documentationCache;

	@Autowired
	private ServiceModelToSwagger2Mapper mapper;

	@Autowired
	private JsonSerializer jsonSerializer;

	@RequestMapping(value = "/v1/api-docs", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Json> getDocumentation(
			@RequestParam(value = "group", required = false) String swaggerGroup, HttpServletRequest req) {

		String groupName = Optional.fromNullable(swaggerGroup).or(Docket.DEFAULT_GROUP_NAME);
		Documentation documentation = documentationCache.documentationByGroup(groupName);
		if (documentation == null) {
			return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
		}
		Swagger swagger = mapper.mapDocumentation(documentation);
		swagger.host(hostName());

		return new ResponseEntity<Json>(jsonSerializer.toJson(swagger), HttpStatus.OK);
	}

	private String hostName() {
		URI uri = linkTo(Swagger2Controller.class).toUri();
		String host = uri.getHost();
		int port = uri.getPort();
		String path = uri.getPath();
		if (port > -1) {
			host += ":" + port;
		}

		if (!path.isEmpty()) {
			host += "/" + path;
		}
		return host;
	}
}