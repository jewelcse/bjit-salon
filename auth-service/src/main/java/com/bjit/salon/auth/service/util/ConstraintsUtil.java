package com.bjit.salon.auth.service.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstraintsUtil {

    public static final String APPLICATION_BASE_URL = "/api/v1";

    public static final String[] APPLICATION_PUBLIC_URL= new String[]{
            APPLICATION_BASE_URL+"/sign-in",
            APPLICATION_BASE_URL+"/sign-up",
            APPLICATION_BASE_URL+"/",
            "/actuator",
            "/actuator/health",
            "/actuator/health/**",
            "/actuator/info"
    };

    public static final String[] APPLICATION_SUPER_ADMIN_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/activateDeactivate"
    };
    public static final String[] APPLICATION_ADMIN_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/"
    };
    public static final String[] APPLICATION_STAFF_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/"
    };
    public static final String[] APPLICATION_USER_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/"
    };


    public static final String APPLICATION_SECRET_KEY = "salon@123";
    public static final long TOKEN_EXPIRATION_TIME = 10000 * 1000;
}
