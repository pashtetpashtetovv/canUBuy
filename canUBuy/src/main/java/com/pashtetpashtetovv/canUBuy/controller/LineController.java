package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.Line;
import com.pashtetpashtetovv.canUBuy.domain.Note;
import com.pashtetpashtetovv.canUBuy.service.LineService;
import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("line")
public class LineController {

    @Autowired
    private final LineService lineService;

    @Autowired
    private final NoteService noteService;

    public LineController(LineService lineService, NoteService noteService) {
        this.lineService = lineService;
        this.noteService = noteService;
    }

    @GetMapping("/creationPage")
    public String createNote(Model model, @ModelAttribute Note note){
        model.addAttribute("line", new Line());
        model.addAttribute("note", note);
        return "lineCreationPage";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Line line, @RequestParam Long noteID, Model model, RedirectAttributes redirectAttributes){
        line.setNote(noteService.findById(noteID).get());
        Line newLine = lineService.create(line);
        redirectAttributes.addAttribute("noteID", noteID);
        return "redirect:/note/{noteID}";
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam Long lineID){
        try {
            lineService.delete(lineID);
        } catch(NoSuchElementException e) {
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.OK;
    }

}
