package com.ecomindo.onboarding.testinghat.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class CustomUserDTO extends User {
	private String fullname;

	public CustomUserDTO(String username, Collection<? extends GrantedAuthority> authorities, String fullname) {
		super(username, "---", authorities);
		this.fullname = fullname;
	}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}