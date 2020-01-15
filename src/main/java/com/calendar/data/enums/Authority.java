package com.calendar.data.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority{
	ADMIN, CUSTOMER;

	@Override
	public String getAuthority() {
		return "ROLE_" + toString();
	}

}
