package org.springframework.social.keycloak.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.keycloak.api.Keycloak;
import org.springframework.social.keycloak.api.KeycloakProfile;

public class KeycloakAdapter implements ApiAdapter<Keycloak>{
	
	public KeycloakAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UserProfile fetchUserProfile(Keycloak keycloak) {
		KeycloakProfile profile = keycloak.userOperations().getUserProfile();
		return new UserProfileBuilder()
			.setUsername(profile.getId())
			.setEmail(profile.getEmail())
			.setName(profile.getName())
			.setFirstName(profile.getFirstName())
			.setLastName(profile.getLastName())
			.build();
	}
 
	@Override
	public void setConnectionValues(Keycloak keycloak, ConnectionValues values) {
		KeycloakProfile profile = keycloak.userOperations().getUserProfile();
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getName());
	}
 
	@Override
	public boolean test(Keycloak keycloak) {
		try {
			keycloak.userOperations().getUserProfile();
            return true;
        } catch (ApiException e) {
            return false;
        }
	}
 
	@Override
	public void updateStatus(Keycloak keycloak, String message) {
		throw new UnsupportedOperationException();
	}

}
