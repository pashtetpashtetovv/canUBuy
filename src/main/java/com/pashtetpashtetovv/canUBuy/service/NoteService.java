package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.Note;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private final NoteRepository noteRepo;

    public NoteService(NoteRepository noteRepo){
        this.noteRepo = noteRepo;
    }

    public List<Note> findAll(){
        return noteRepo.findAll();
    }

    public Note createNote(Note note){
        return noteRepo.save(note);
    }

    public Optional<Note> findById(Long id){
        return noteRepo.findById(id);
    }

    public void delete(Long id){
        noteRepo.deleteById(id);
    }
}
