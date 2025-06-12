package org.sopt.global.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("게시판 API")
				.version("v1.0.0")
				.description("SOPT 36기 과제 - 게시판 서비스를 위한 API입니다"))
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
				.components(new Components()
					.addSecuritySchemes(SECURITY_SCHEME_NAME,
					new SecurityScheme()
						.name(SECURITY_SCHEME_NAME)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")));
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
			.group("public-api")
			.pathsToMatch("/**")
			.packagesToScan("org.sopt")
			.packagesToExclude("org.sopt.global.exception") // 예외 처리 패키지 제외
			.build();
	}
}
