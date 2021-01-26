package org.springframework.social.keycloak.api;

import org.springframework.social.ApiBinding;

public interface Keycloak extends ApiBinding {
	 
		UserOperations userOperations();

}
