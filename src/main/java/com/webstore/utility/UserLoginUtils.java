package com.webstore.utility;

import com.webstore.security.UserLogin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUtils {

    private UserLoginUtils() {
    }

    public static UserLogin getUserLogin() {
        Object principal;

        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException e) {
            return null;
        }

        if (principal == null || !(principal instanceof UserLogin)) {
            return null;
        }

        return (UserLogin) principal;
    }
}