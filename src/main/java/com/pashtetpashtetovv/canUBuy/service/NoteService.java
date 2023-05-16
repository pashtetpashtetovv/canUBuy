package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDto;
import com.pashtetpashtetovv.canUBuy.domain.model.Line;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.utils.exception.NotFoundException;
import com.pashtetpashtetovv.canUBuy.mapper.NoteMapper;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import com.pashtetpashtetovv.canUBuy.utils.exception.RestrictedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private final NoteRepository noteRepo;

    @Autowired
    private final NoteMapper noteMapper;

    @Autowired
    private final UserService userService;

    public NoteService(NoteRepository noteRepo, NoteMapper noteMapper, UserService userService){
        this.noteRepo = noteRepo;
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public List<Note> findAll(){
        return noteRepo.findAll();
    }

    public Note createNote(Note note){
        return noteRepo.save(note);
    }

    public Note createNote(NoteDto dto){
        dto.setOwnerLogin(userService.getAuthenticatedUsername());
        final Note note = noteMapper.toEntity(dto);
        return noteRepo.save(note);
    }

    public Note findById(Long id){
        final Note note =  noteRepo.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException(
                                String.format("Note with id: %d not found", id)
                        )
                );

        final String ownerLogin =
                note.getOwner() == null ? null : note.getOwner().getLogin();

        userService.isUserFreeToSeePage(ownerLogin);

        return note;
    }

    public Note findById(Long id, Model model){
        final Note note = noteRepo.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException(String.format("Note with id: %d not found", id))
                );

        final String ownerLogin =
                note.getOwner() == null ? null : note.getOwner().getLogin();

        userService.isUserFreeToSeePage(ownerLogin);

        model.addAttribute("line", new Line());
        model.addAttribute("note", note);
        model.addAttribute("linesList", note.getLines());
        return note;
    }

    public void delete(Long id){
        if(id == null){
            return;
        }
        noteRepo.deleteById(id);
    }

    public List<Note> findByOwnerLogin(String login){
        return noteRepo.findByOwner_Login(login);
    }

    public List<Note> findByAuth(){
        return findByOwnerLogin(
                userService.getAuthenticatedUsername()
        );
    }

    public List<Note> findByAuth(Model model){
        model.addAttribute("notesList", findByAuth());
        return findByOwnerLogin(
                userService.getAuthenticatedUsername()
        );
    }
}
