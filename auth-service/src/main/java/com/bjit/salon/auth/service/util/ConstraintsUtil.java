package com.bjit.salon.auth.service.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstraintsUtil {

    public static final String APPLICATION_BASE_URL = "/api/v1";


    // gateway urls
    public static final String SALON_SERVICE ="/salons-service/api/v1";
    public static final String STAFF_SERVICE ="/staffs-service/api/v1";
    public static final String SALON_STAFF_AG_SERVICE ="/salons-staff-ag-service/api/v1";

    public static final String[] APPLICATION_PUBLIC_URL= new String[]{
            APPLICATION_BASE_URL+"/sign-in",
            APPLICATION_BASE_URL+"/sign-up",
            APPLICATION_BASE_URL+"/",
            APPLICATION_BASE_URL+"/salons/**",
            SALON_STAFF_AG_SERVICE+"/salons/**",
            // todo: make this url public, now for testing purpose it's being made public directly
            SALON_SERVICE+"/salons/search?q=**",
            "/actuator",
            "/actuator/health",
            "/actuator/health/**",
            "/actuator/info"
    };

    public static final String[] APPLICATION_SUPER_ADMIN_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/activateDeactivate", // activate/deactivate the user
            SALON_SERVICE+"/salons", // manage salons
            STAFF_SERVICE+"/staffs", // manage all staffs

    };
    public static final String[] APPLICATION_ADMIN_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/",
            SALON_SERVICE+"/salons", // create,update, salons
            SALON_SERVICE+"/salons/**", // view salon
            STAFF_SERVICE+"/staffs", // create, update staffs
            STAFF_SERVICE+"/salons/**/staffs", // view all staffs in a salon
            STAFF_SERVICE+"/salons/**/available/staffs", // view all available staffs in a salon

    };
    public static final String[] APPLICATION_STAFF_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/",

    };
    public static final String[] APPLICATION_USER_ACCESSIBLE_URL= new String[]{
            APPLICATION_BASE_URL+"/"
    };


    public static final String APPLICATION_SECRET_KEY = "salon@123";
    public static final long TOKEN_EXPIRATION_TIME = 10000 * 1000;
}
