package com.company.util.springSicurityUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {
    public  static  String getBCrypt(String password){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.encode(password);
    }
}
