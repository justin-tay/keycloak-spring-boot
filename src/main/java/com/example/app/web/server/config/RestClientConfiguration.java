package com.example.app.web.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

/**
 * Rest client configuration.
 */
@Configuration
public class RestClientConfiguration {

	@Bean
	RestClient restClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		return RestClient.builder()
			.requestInterceptor(new OAuth2ClientHttpRequestInterceptor(authorizedClientManager))
			.build();
	}

}