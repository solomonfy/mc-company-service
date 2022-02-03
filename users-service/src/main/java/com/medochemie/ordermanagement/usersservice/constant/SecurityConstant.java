package com.medochemie.ordermanagement.usersservice.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token"; //custom http header to attach the actual token
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String SOLOMON = "Solomon"; // token issuer can be your company or something
    public static final String MC_ADMIN = "User portal"; // who is the audience of the token or user of the token e.g admin, finance, sales
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page"; //a message for any attempt to access an authorized resource/page
    public static final String ACCESS_DENIED_MESSAGE = "You don't have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "Options"; //if the http method is options, don't need to do anything
    public static final String[] PUBLIC_URLS = { "/users/logon", "/users/register", "/users/resetpassword/**", "/users/image/**"}; //no need of authentication for these urls
    // for "/users/resetpassword/**" the ** is because email id will be appended at the end like /users/resetpassword/abc@abc.com
}
