package com.ecomesbe.dicejwt.security;

public class Constants {
	
	//Spring Security
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	//JWT
	public static final String ISSUER_INFO = "enric";
	public static final String SECRET_KEY = "enric";
	public static final long TOKEN_EXPIRATION_TIME = 846_000_000; //10 days


}
