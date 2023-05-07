package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDto;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.utils.exception.NotFoundException;
import com.pashtetpashtetovv.canUBuy.mapper.NoteMapper;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private final NoteRepository noteRepo;

    @Autowired
    private final NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepo, NoteMapper noteMapper){
        this.noteRepo = noteRepo;
        this.noteMapper = noteMapper;
    }

    public List<Note> findAll(){
        return noteRepo.findAll();
    }

    public Note createNote(Note note){
        return noteRepo.save(note);
    }

    public Note createNote(NoteDto dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        dto.setOwnerLogin(auth.getName());
        Note note = noteMapper.toEntity(dto);
        return noteRepo.save(note);
    }

    public Note findById(Long id){
        return noteRepo.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException(String.format("Note with id: %d not found", id))
                );
    }

    public void delete(Long id){
        noteRepo.deleteById(id);
    }

    public List<Note> findByOwnerLogin(String login){
        return noteRepo.findByOwner_LoginIgnoreCase(login);
    }

    public List<Note> findByAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            throw new NotFoundException("User is Null!");
        }
        return findByOwnerLogin(auth.getName());
    }
}
