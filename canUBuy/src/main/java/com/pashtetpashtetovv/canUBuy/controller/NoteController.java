package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.Note;
import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import java.util.List;

@Controller
@RequestMapping("note")
class NoteController {

    @Autowired
    private final NoteRepository noteRepo;

    @Autowired
    private final NoteService noteService;

    NoteController(NoteRepository noteRepo,NoteService noteService) {
        this.noteService = noteService;
        this.noteRepo = noteRepo;
    }

    @GetMapping("/creationPage")
    public String createNote(Model model){
        model.addAttribute("note", new Note());
        return "creationPage";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Note note, Model model){
        model.addAttribute("note", noteService.createNote(note));
        return "note";
    }

    @GetMapping("/getAll")
    List<Note> getAll(){
        return noteRepo.findAll();
    }

    @GetMapping("/getAllTest")
    public String getAllTest(Model model){
        model.addAttribute("notesList", noteService.findAllNotes());
        return "allNotes";
    }

}
