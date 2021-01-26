package org.springframework.social.keycloak.api.impl;

import org.springframework.social.MissingAuthorizationException;

import java.net.URI;
 
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class AbstractKeycloakApiOperations {
	
	protected final boolean isAuthorized;
	private String baseUrl;
 
	public AbstractKeycloakApiOperations(boolean isAuthorized, String baseUrl) {
		this.isAuthorized = isAuthorized;
		this.baseUrl=baseUrl;
	}
 
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("No authorization");
		}
	}
	
	
	protected URI buildUri(String path) {
		return buildUri(path, EMPTY_PARAMETERS);
	}
	
	protected URI buildUri(String path, String parameterName, String parameterValue) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set(parameterName, parameterValue);
		return buildUri(path, parameters);
	}

	protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
		//return URIBuilder.fromUri(API_URL_BASE + path).queryParams(parameters).build();
		return URIBuilder.fromUri(this.baseUrl + path).queryParams(parameters).build();
	}
	
	//private static final String API_URL_BASE = "https://identity.workspace.witcom.de:443/oauth2/";
 
	private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

}
