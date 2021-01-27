package org.springframework.social.keycloak.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.keycloak.api.KeycloakProfile;
import org.springframework.social.keycloak.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractKeycloakApiOperations implements UserOperations {
	
	private final RestTemplate restTemplate;
	private final Logger log = LoggerFactory.getLogger(UserTemplate.class);
	
	public UserTemplate(RestTemplate restTemplate, boolean isAuthorized, String baseUrl) {
		// TODO
		super(isAuthorized,baseUrl);
		this.restTemplate = restTemplate;
		//this.baseUrl=baseUrl;
	}
 
	@Override
	public KeycloakProfile getUserProfile() {
		requireAuthorization();
		log.debug("Get UserProfile from {}", buildUri("/protocol/openid-connect/userinfo").toString());
		
		return restTemplate.getForObject(buildUri("/protocol/openid-connect/userinfo"), KeycloakProfile.class);
	}
	

}
