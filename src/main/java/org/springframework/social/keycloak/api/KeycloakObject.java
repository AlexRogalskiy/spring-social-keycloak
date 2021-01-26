package org.springframework.social.keycloak.api;

import java.util.HashMap;
import java.util.Map;

public abstract class KeycloakObject {
	
	private Map<String, Object> extraData;
	
	public KeycloakObject() {
		this.extraData = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getExtraData() {
		return extraData;
	}
	
	/**
	 * {@link JsonAnySetter} hook. Called when an otherwise unmapped property is being processed during JSON deserialization.
	 * @param key The property's key.
	 * @param value The property's value.
	 */
	protected void add(String key, Object value) {
		extraData.put(key, value);
	}

}
