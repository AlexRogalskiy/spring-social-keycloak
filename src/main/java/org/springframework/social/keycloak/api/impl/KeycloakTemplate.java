package org.springframework.social.keycloak.api.impl;

import javax.inject.Inject;

import org.springframework.social.keycloak.api.Keycloak;
import org.springframework.social.keycloak.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.oauth2.TokenStrategy;

public class KeycloakTemplate  extends AbstractOAuth2ApiBinding implements Keycloak{
	
	private String accessToken;
	private UserOperations userOperations;
	private String baseUrl;
	
	public KeycloakTemplate() {
		// TODO Auto-generated constructor stub
		initialize();
	}
 
	public KeycloakTemplate(String accessToken,String baseUrl) {
		super(accessToken);
		this.accessToken = accessToken;
		this.baseUrl=baseUrl;
		initialize();
	}
 
	public KeycloakTemplate(String accessToken, TokenStrategy tokenStrategy) {
		super(accessToken, tokenStrategy);
		initialize();
	}
	
	private void initialize() {
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized(),this.baseUrl);
	}
	
	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.BEARER;
	}

	@Override
	public UserOperations userOperations() {
		// TODO Auto-generated method stub
		
		return this.userOperations;
	}

}
