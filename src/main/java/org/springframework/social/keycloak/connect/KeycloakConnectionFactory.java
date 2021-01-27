package org.springframework.social.keycloak.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.keycloak.api.Keycloak;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class KeycloakConnectionFactory extends OAuth2ConnectionFactory<Keycloak> {

	private final Logger log = LoggerFactory.getLogger(KeycloakConnectionFactory.class);
	private boolean useStateParameter = true;

	/**
	 * @param baseUrl
	 * @param clientId
	 * @param clientSecret
	 */
	public KeycloakConnectionFactory(String baseUrl, String clientId, String clientSecret) {
		
		super("keycloak", new KeycloakServiceProvider(baseUrl, clientId, clientSecret), new KeycloakAdapter());
		if(baseUrl==null) {
			throw new RuntimeException("Keycloak base-url not set");
		}
		log.debug("Done with initializing Keycloak Connection Factory");
	}

	/**
	 * @param baseUrl
	 * @param clientId
	 * @param clientSecret
	 * @param useStateParameter
	 */
	public KeycloakConnectionFactory(String baseUrl, String clientId, String clientSecret, boolean useStateParameter) {
		super("keycloak", new KeycloakServiceProvider(baseUrl, clientId, clientSecret), new KeycloakAdapter());
		this.useStateParameter = useStateParameter;
		log.debug("Done with initializing Keycloak Connection Factory");

	}

	@Override
	public boolean supportsStateParameter() {
		return useStateParameter;
	}

	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		Keycloak api = ((KeycloakServiceProvider) getServiceProvider()).getApi(accessGrant.getAccessToken());
		UserProfile userProfile = getApiAdapter().fetchUserProfile(api);
		return userProfile.getUsername();
	}

}
