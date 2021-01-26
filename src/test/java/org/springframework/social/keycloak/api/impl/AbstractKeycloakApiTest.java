package org.springframework.social.keycloak.api.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

public class AbstractKeycloakApiTest {
	
	protected static final String ACCESS_TOKEN = "someAccessToken";
	protected KeycloakTemplate witIdentity;
	
	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer unauthorizedMockServer;
	protected MockRestServiceServer appFacebookMockServer;
	
	public AbstractKeycloakApiTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void setup(){
		witIdentity = new KeycloakTemplate(ACCESS_TOKEN,"https://auth.witcom.services/auth/realms/witcom");
		mockServer = MockRestServiceServer.createServer(witIdentity.getRestTemplate());
	}
 
	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

}
