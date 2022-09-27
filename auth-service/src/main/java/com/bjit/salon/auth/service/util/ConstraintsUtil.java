package com.bjit.salon.auth.service.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstraintsUtil {

    public static final String APPLICATION_BASE_URL = "api/v1";

    public static final String[] APPLICATION_PUBLIC_URL= new String[]{
            "/api/v1/sign-in",
            "/api/v1/sign-up"
    };


    public static final String APPLICATION_SECRET_KEY = "salon@123";
    public static final long TOKEN_EXPIRATION_TIME = 100 * 1000;
}
