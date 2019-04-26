package com.cognizant.openshift.poc.usermgmtservice.security;

public class SecurityConstants {
	
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String EXPIRATION_TIME = "86400000";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/create";
    public static final String LOGIN_URL = "/login";
}
