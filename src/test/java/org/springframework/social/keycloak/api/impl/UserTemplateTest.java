package org.springframework.social.keycloak.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
 
import java.util.List;
import java.util.Map;
 
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.keycloak.api.KeycloakProfile;

public class UserTemplateTest extends AbstractKeycloakApiTest{
	
	//@Ignore
	@Test
	public void getUserProfile() throws Exception{
		mockServer.expect(requestTo("https://auth.witcom.services/auth/realms/witcom/protocol/openid-connect/userinfo"))
		.andExpect(method(GET))
		.andRespond(withSuccess(jsonResource("wit-identity-profile"), APPLICATION_JSON));
		
		KeycloakProfile profile = witIdentity.userOperations().getUserProfile();
		assertEquals("c.buchberger@witcom.de", profile.getEmail());
		
	}
	

}
