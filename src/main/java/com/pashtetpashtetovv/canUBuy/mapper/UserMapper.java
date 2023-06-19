package com.pashtetpashtetovv.canUBuy.mapper;

import com.pashtetpashtetovv.canUBuy.domain.dto.UserDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        if(user == null) {
            return null;
        }
        return new UserDTO(
                user.getLogin(),
                user.getNotes(),
                user.getRoles(),
                user.getFriends());
    }
}
