package com.pashtetpashtetovv.canUBuy.config.db;

import com.pashtetpashtetovv.canUBuy.config.security.role.Role;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class InitUsers implements ApplicationRunner {

    @Autowired
    private final UserService userService;

    public InitUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createAdmin();
        createCasualUser();
    }

    private void createAdmin(){
        User admin = null;
        final String adminLogin = "admin";
        final String adminPassword = "123456";
        try {
            admin = (User) userService.loadUserByUsername(adminLogin);
        } catch(UsernameNotFoundException e){
            admin = new User();
        } finally {
            admin.setLogin(adminLogin);
            admin.setPassword(adminPassword);
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);
            admin.setRoles(roles);
            userService.create(admin);
        }
    }

    private void createCasualUser(){
        User casualUser = null;
        final String login = "user";
        final String password = "123456";
        try {
            casualUser = (User) userService.loadUserByUsername(login);
        } catch(UsernameNotFoundException e){
            casualUser = new User();
        } finally {
            casualUser.setLogin(login);
            casualUser.setPassword(password);
            casualUser.setRoles(Collections.singleton(Role.USER));
            userService.create(casualUser);
        }
    }
}
