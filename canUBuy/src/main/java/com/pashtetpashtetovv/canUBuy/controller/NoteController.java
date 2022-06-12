package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.Note;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import java.util.List;

@Controller
@RequestMapping("note")
class NoteController {

    private final NoteRepository noteRepo;

    NoteController(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    @PostMapping("/create")
    String createNote(@RequestBody Note newNote){
        return noteRepo.save(newNote).toString();
    }

    @GetMapping("/getAll")
    List<Note> getAll(){
        return noteRepo.findAll();
    }
}
