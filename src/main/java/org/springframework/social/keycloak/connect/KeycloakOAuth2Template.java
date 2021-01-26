package org.springframework.social.keycloak.connect;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.OAuth2Template;
 
import java.util.Map;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.MultiValueMap;

public class KeycloakOAuth2Template extends OAuth2Template{
	
	//private final Logger log = LoggerFactory.getLogger(WSO2ISOAuth2Template.class);
	 
	public KeycloakOAuth2Template(String clientId, String clientSecret,
			String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
 
	public KeycloakOAuth2Template(String clientId, String clientSecret,
			String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, authenticateUrl,
				accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
 
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		//log.debug("accessTokenUrl {}", accessTokenUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		//log.debug("Request  {}", requestEntity);
		ResponseEntity<Map> responseEntity = getRestTemplate().exchange(accessTokenUrl, HttpMethod.POST, requestEntity, Map.class);
		Map<String, Object> responseMap = responseEntity.getBody();
		return extractAccessGrant(responseMap);
	}
	
	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		
		String accessToken = (String) result.get("access_token");
		String scope = (String) result.get("scope");
		String refreshToken = (String) result.get("refresh_token");
		Integer expiresIn = (Integer) result.get("expires_in");
		
		//log.debug("Extracted from Grant at: {}, rt: {}, expires in {}", accessToken,refreshToken,expiresIn);
		return createAccessGrant(accessToken, scope, refreshToken, new Long(expiresIn), result);
		
	}

}
