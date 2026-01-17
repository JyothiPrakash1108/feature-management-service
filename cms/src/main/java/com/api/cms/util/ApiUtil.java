package com.api.cms.util;

import java.util.UUID;

public class ApiUtil {
    public static String generateApiKey(){
        return UUID.randomUUID().toString();
    }
}
