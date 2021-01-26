package org.springframework.social.keycloak.api.impl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.keycloak.api.KeycloakProfile;
import org.springframework.social.keycloak.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractKeycloakApiOperations implements UserOperations {
	
	private final RestTemplate restTemplate;
	//private final Logger log = LoggerFactory.getLogger(UserTemplate.class);
	
	public UserTemplate(RestTemplate restTemplate, boolean isAuthorized, String baseUrl) {
		// TODO Auto-generated constructor stub
		super(isAuthorized,baseUrl);
		this.restTemplate = restTemplate;
		//this.baseUrl=baseUrl;
	}
 
	@Override
	public KeycloakProfile getUserProfile() {
		// TODO Auto-generated method stub
		requireAuthorization();
		//log.debug("Result" + restTemplate.getForObject(buildUri("userinfo?schema=openid"), String.class));
		
		return restTemplate.getForObject(buildUri("/protocol/openid-connect/userinfo"), KeycloakProfile.class);
	}
	

}
