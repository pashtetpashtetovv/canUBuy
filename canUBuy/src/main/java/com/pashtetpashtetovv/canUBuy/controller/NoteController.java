package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.Line;
import com.pashtetpashtetovv.canUBuy.domain.Note;
import com.pashtetpashtetovv.canUBuy.service.LineService;
import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pashtetpashtetovv.canUBuy.repository.NoteRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("note")
class NoteController {

    @Autowired
    private final NoteRepository noteRepo;

    @Autowired
    private final NoteService noteService;

    @Autowired
    private final LineService lineService;

    NoteController(NoteRepository noteRepo, NoteService noteService, LineService lineService) {
        this.noteService = noteService;
        this.noteRepo = noteRepo;
        this.lineService = lineService;
    }

    @GetMapping("/creationPage")
    public String createNote(Model model){
        model.addAttribute("note", new Note());
        return "creationPage";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Note note, Model model, RedirectAttributes redirectAttributes){
        Note newNote = noteService.createNote(note);
        //System.out.println(newNote.getId());
        //model.addAttribute("note", newNote);
        //model.addAttribute("id", newNote.getId());
        redirectAttributes.addAttribute("id", newNote.getId());
        return "redirect:/note/{id}";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model){
        final Note note = noteService.findById(id).get();
        model.addAttribute("line", new Line());
        model.addAttribute("note", note);
        model.addAttribute("linesList", lineService.findByNote(note));
        return "note";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("notesList", noteService.findAll());
        return "allNotes";
    }

}
