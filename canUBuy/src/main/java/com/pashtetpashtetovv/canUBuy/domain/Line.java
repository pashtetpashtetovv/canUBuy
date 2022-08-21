package com.pashtetpashtetovv.canUBuy.domain;

import com.pashtetpashtetovv.canUBuy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Line {

    //@Autowired
    //private final NoteService noteService;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Autowired
    @ManyToOne(targetEntity = Note.class, fetch = FetchType.LAZY)
    private Note note;

    /*
    public Line(NoteService noteService, Long id, StringBuffer description, Long noteId){
        this.noteService = noteService;
        this.id = id;
        this.description = description;
        this.note = this.noteService.findById(noteId).get();
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
