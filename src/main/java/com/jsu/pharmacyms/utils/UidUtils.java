package com.jsu.pharmacyms.utils;

import java.util.Random;

public class UidUtils {
    public static String getFiveUid(){
        String uid="";
        Random random=new Random();
        for(int i=0;i<5;i++){
            uid=uid+random.nextInt(10);
        }
        return uid;
    }
}
