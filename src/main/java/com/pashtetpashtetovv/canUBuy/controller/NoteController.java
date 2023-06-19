package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("note")
class NoteController {

    private final NoteService noteService;

    private final Logger log = LoggerFactory.getLogger(NoteController.class);

    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/create")
    public String showCreateNotePage(Model model){
        model.addAttribute("note", new NoteDTO());
        return "noteCreationPage";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute NoteDTO noteDto, RedirectAttributes redirectAttributes){
        //Note newNote = noteService.createNote(noteDto);
        redirectAttributes.addAttribute("id", noteService.createNote(noteDto).getId());
        return "redirect:/note/{id}";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model){
        noteService.findById(id, model);
        return "note";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        noteService.findByAuth(model);
        return "allNotes";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long noteID) {
        noteService.delete(noteID);
        return "redirect:/note/all";
    }

}
