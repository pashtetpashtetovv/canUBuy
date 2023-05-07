package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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

    public User create(User user){
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
}
