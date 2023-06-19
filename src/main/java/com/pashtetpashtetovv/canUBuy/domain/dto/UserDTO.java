package com.pashtetpashtetovv.canUBuy.domain.dto;

import com.pashtetpashtetovv.canUBuy.config.security.role.Role;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.domain.model.User;

import java.util.Set;

public record UserDTO(String login, Set<Note> note, Set<Role> roles, Set<User> friends) {}