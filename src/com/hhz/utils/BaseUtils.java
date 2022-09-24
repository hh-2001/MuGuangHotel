package com.hhz.utils;

import java.util.UUID;

public class BaseUtils {

    //生成uuid
    public static String getUuid(){
        String s = UUID.randomUUID().toString();
        s.replace("-","");
        return s.substring(0,8);
    }


}
