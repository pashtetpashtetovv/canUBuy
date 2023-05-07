package com.pashtetpashtetovv.canUBuy.mapper;

import com.pashtetpashtetovv.canUBuy.domain.buider.NoteBuilder;
import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDto;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(NoteMapper.class);

    public Note toEntity(NoteDto dto){
        if(dto == null){
            return null;
        }
        NoteBuilder nb = new NoteBuilder();
        User owner = null;
        try{
            owner = (User) userService.loadUserByUsername(dto.getOwnerLogin());
        } catch(UsernameNotFoundException e){
            log.error("User with login: {} not found", dto.getOwnerLogin());
        }

        return nb.title(dto.getTitle())
                .description(dto.getDescription())
                .owner(owner)
                .build();
    }

}
