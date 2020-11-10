package com.ecomesbe.dicejwt.security;

public class Constants {
	
	//Spring Security
	public static final String AUTH_LOGIN_URL = "dicegame/login";
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	
	//JWT
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";
	public static final String SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 846_000_000; //10 days

	private Constants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
