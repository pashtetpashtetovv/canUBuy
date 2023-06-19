package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.config.security.role.Role;
import com.pashtetpashtetovv.canUBuy.domain.dto.FriendRequestDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.mapper.UserMapper;
import com.pashtetpashtetovv.canUBuy.repository.UserRepository;
import com.pashtetpashtetovv.canUBuy.utils.exception.DuplicateException;
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

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private FriendRequestService friendRequestService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public FriendRequestService getFriendRequestService() {
        return friendRequestService;
    }

    @Autowired
    public void setFriendRequestService(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("User with login %s  not found", username));
        }
        return user;
    }

    public UserDetails loadUserByUsernameAndAddToModel(String username, Model model){
        isUserFreeToSeePage(username);
        User user = (User) loadUserByUsername(username);
        model.addAttribute("user", UserMapper.toDto(user));
        model.addAttribute("friendRequestDTO", new FriendRequestDTO());
        model.addAttribute("friendRequests", friendRequestService.findByTarget(user));
        return user;
    }

    public User create(User user){

        boolean loginDuplicated = userRepository.existsByLogin(user.getLogin());
        if(loginDuplicated){
            throw new DuplicateException(
                    String.format("User with login %s already exist", user.getLogin())
            );
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        if(user.getRoles() == null) {
            user.setRoles(
                    Collections.singleton(Role.USER)
            );
        }
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
                                        .equals(Role.ADMIN.getCode())
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

    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }

    public User getAuthenticatedUser(){
        return (User) loadUserByUsername(getAuthenticatedUsername());
    }
}
