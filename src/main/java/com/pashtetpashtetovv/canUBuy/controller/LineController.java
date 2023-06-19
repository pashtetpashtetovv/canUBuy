package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.model.Line;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.service.LineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("line")
public class LineController {

    private final LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Line line, @RequestParam Long noteID, Model model, RedirectAttributes redirectAttributes){
        lineService.createAndSetNote(line, noteID);
        redirectAttributes.addAttribute("noteID", noteID);
        return "redirect:/note/{noteID}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long lineID, Model model, RedirectAttributes redirectAttributes){
        Note note = lineService.getById(lineID).getNote();
        lineService.delete(lineID);
        redirectAttributes.addAttribute("noteID", note.getId());
        return "redirect:/note/{noteID}";
    }
}
