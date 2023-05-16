package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.config.security.role.Role;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.repository.UserRepository;
import com.pashtetpashtetovv.canUBuy.utils.exception.NotFoundException;
import com.pashtetpashtetovv.canUBuy.utils.exception.RestrictedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("User with login %s  not found", username)
            );
        }
        return user;
    }

    public UserDetails loadUserByUsername(String username, Model model){
        isUserFreeToSeePage(username);
        User user = (User) loadUserByUsername(username);
        model.addAttribute("user", user);
        return user;
    }

    public User create(User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        if(user.getRoles().isEmpty())
            user.setRoles(
                    Collections.singleton(Role.USER)
            );
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    String getAuthenticatedUsername(){
        Authentication auth = getAuth();
        if(auth == null){
            throw new NotFoundException("User is Null!");
        }
        return auth.getName();
    }

    private boolean isCurrentUserAdmin(){
        Authentication auth = getAuth();
        return auth.getAuthorities()
                .stream()
                .anyMatch(
                        grantedAuthority ->
                                grantedAuthority.getAuthority()
                                        .equals("ADMIN")
                );
    }

    public boolean isUserFreeToSeePage(String suspectLogin){
        final boolean isCurrentUserAdmin = isCurrentUserAdmin();
        if(isCurrentUserAdmin){
            return true;
        }
        final String currentUserLogin = getAuthenticatedUsername();
        if(currentUserLogin.equals("anonymousUser") || !currentUserLogin.equals(suspectLogin)){
            throw new RestrictedAccessException("Sorry, you don`t have access to that page");
        }
        return true;
    }

}
