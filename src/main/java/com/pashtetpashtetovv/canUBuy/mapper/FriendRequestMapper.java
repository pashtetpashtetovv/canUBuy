package com.pashtetpashtetovv.canUBuy.mapper;

import com.pashtetpashtetovv.canUBuy.domain.dto.FriendRequestDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.FriendRequest;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestMapper {

    private final UserService userService;

    private final static Logger log = LoggerFactory.getLogger(FriendRequestMapper.class);

    public FriendRequestMapper(@Lazy UserService userService) {
        this.userService = userService;
    }

    public FriendRequest toEntity(FriendRequestDTO dto){
        if(dto == null) {
            return null;
        }
        User requester;
        User target;
        try{
            requester = (User) userService.loadUserByUsername(dto.getLoginFrom());
        } catch(UsernameNotFoundException e){
            log.error("Requester with login {} not found", dto.getLoginFrom());
            return null;
        }

        try{
            target = (User) userService.loadUserByUsername(dto.getLoginTo());
        } catch(UsernameNotFoundException e){
            log.error("User with login {} not found", dto.getLoginFrom());
            return null;
        }

        return new FriendRequest(requester, target);
    }
}
