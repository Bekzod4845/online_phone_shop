package com.company.util.springSicurityUtil;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SpringSecurityUtil {
        public static User getCurrentUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User) authentication.getPrincipal();
        }
    }

