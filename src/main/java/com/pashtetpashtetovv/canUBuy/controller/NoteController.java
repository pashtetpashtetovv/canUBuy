package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.dto.NoteDto;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("note")
class NoteController {

    @Autowired
    private final NoteService noteService;

    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/create")
    public String createNote(Model model){
        model.addAttribute("note", new NoteDto());
        return "noteCreationPage";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute NoteDto noteDto, RedirectAttributes redirectAttributes){
        Note newNote = noteService.createNote(noteDto);
        redirectAttributes.addAttribute("id", newNote.getId());
        return "redirect:/note/{id}";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model){
        noteService.findById(id, model);
        return "note";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        noteService.findByAuth(model);
        return "allNotes";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long noteID) {
        noteService.delete(noteID);
        return "redirect:/note/getAll";
    }

}
