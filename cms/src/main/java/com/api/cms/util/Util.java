package com.api.cms.util;

import java.util.UUID;

public class Util {
    public static String generateApiKey(){
        return UUID.randomUUID().toString();
    }

    public static UUID generateUUID() {
       return UUID.randomUUID();
    }
}
