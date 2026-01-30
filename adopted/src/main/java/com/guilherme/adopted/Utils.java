package com.guilherme.adopted;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.guilherme.adopted.models.User;

public class Utils {


    public static User getUserAuthenticate(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }


}
