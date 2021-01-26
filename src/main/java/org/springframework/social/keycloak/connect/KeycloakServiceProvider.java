package org.springframework.social.keycloak.connect;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.keycloak.api.Keycloak;
import org.springframework.social.keycloak.api.impl.KeycloakTemplate;

public class KeycloakServiceProvider extends AbstractOAuth2ServiceProvider<Keycloak>{
	
	private String baseUri;
	
	public KeycloakServiceProvider(String baseUrl,String clientId, String clientSecret) {
		super(createOAuth2Template(baseUrl, clientId, clientSecret));
		this.baseUri = baseUrl;
	}
	
	private static OAuth2Template createOAuth2Template(String baseUrl,String clientId, String clientSecret) {
		String authUrl = baseUrl + "/protocol/openid-connect/auth";
		String tokenUrl = baseUrl + "/protocol/openid-connect/token";
		
		OAuth2Template oAuth2Template = new OAuth2Template(clientId, clientSecret, authUrl, tokenUrl);
		oAuth2Template.setUseParametersForClientAuthentication(true);
		return oAuth2Template;
	}
	/*
	public KeycloakServiceProvider(String clientId, String clientSecret,
			String authorizeUrl, String authenticateUrl, String accessTokenUrl,String userInfoUri){
		super(new WSO2ISOAuth2Template(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl));
		this.userInfoUri = userInfoUri;
		log.info("UserinfoUri:{}, Auth URL :{}, TokenURL: {}   ", userInfoUri,authorizeUrl,accessTokenUrl);
		
	}
	
	public KeycloakServiceProvider(String clientId, String clientSecret,
			String authorizeUrl, String accessTokenUrl,String userInfoUri){
		super(new WSO2ISOAuth2Template(clientId, clientSecret, authorizeUrl, accessTokenUrl));
		this.userInfoUri = userInfoUri;
		log.info("UserinfoUri:{},Auth URL :{}, TokenURL: {}   ", userInfoUri,authorizeUrl,accessTokenUrl);
	}
	*/
 
	@Override
	public Keycloak getApi(String accessToken) {
		
		return new KeycloakTemplate(accessToken,this.baseUri);
	}

	
}
