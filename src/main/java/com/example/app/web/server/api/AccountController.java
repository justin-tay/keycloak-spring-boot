package com.example.app.web.server.api;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * Account endpoint.
 */
@RestController
public class AccountController {

	private final RestClient restClient;

	public AccountController(RestClient restClient) {
		this.restClient = restClient;
	}

	@GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public String account(@RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient) {
		String issuerUri = authorizedClient.getClientRegistration().getProviderDetails().getIssuerUri();
		String resourceUri = issuerUri + "/account/?userProfileMetadata=true";
		return this.restClient.get()
			.uri(resourceUri)
			.accept(MediaType.APPLICATION_JSON)
			.attributes(clientRegistrationId(authorizedClient.getClientRegistration().getRegistrationId()))
			.retrieve()
			.body(String.class);
	}

}