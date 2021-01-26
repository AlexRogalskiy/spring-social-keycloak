package org.springframework.social.keycloak.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeycloakProfile extends KeycloakObject implements Serializable {
	 
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("preferred_username")
		private String id;
		@JsonProperty("email")
		private String email;
		@JsonProperty("name")
		private String name;
		@JsonProperty("given_name")
		private String firstName;
		@JsonProperty("family_name")
		private String lastName;
		
		public KeycloakProfile() {
		}
	 
		public String getId() {
			return id;
		}
	 
		public String getEmail() {
			return email;
		}
	 
		public String getName() {
			return name;
		}
	 
	 
		public String getFirstName() {
			return firstName;
		}
	 
	 
		public String getLastName() {
			return lastName;
		}
	 
	 
		public void setId(String id) {
			this.id = id;
		}
	 
	 
		public void setEmail(String email) {
			this.email = email;
		}
	 
	 
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String toString(){
			return this.getId() + " " + this.getName();
			
		}

}
