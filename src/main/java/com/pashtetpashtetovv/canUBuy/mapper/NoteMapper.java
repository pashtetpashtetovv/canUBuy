package com.pashtetpashtetovv.canUBuy.mapper;

import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    private final  UserService userService;

    Logger log = LoggerFactory.getLogger(NoteMapper.class);

    public NoteMapper(UserService userService) {
        this.userService = userService;
    }

    public Note toEntity(NoteDTO dto){
        if(dto == null){
            return null;
        }
        User owner = null;
        try {
            owner = (User) userService.loadUserByUsername(dto.getOwnerLogin());
        } catch(UsernameNotFoundException e){
            log.error("User with login {} not found", dto.getOwnerLogin());
        }
        return new Note(dto.getTitle(), dto.getDescription(), owner);
    }

}
