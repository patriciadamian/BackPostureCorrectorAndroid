package com.example.backposturecorrector;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

public class Session {

    public static String TOKEN;
    public static boolean IS_USER_PROFILE_COMPLETE = false;
    public static boolean IS_CALIBRATION_COMPLETE = false;

    public static long getId() {
        JWT parsedJWT = new JWT(TOKEN);
        Claim subscriptionMetaData = parsedJWT.getClaim("id");
        String id = subscriptionMetaData.asString();
        return Long.parseLong(id);
    }
}
