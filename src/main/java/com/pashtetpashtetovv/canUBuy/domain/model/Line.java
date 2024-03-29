package com.pashtetpashtetovv.canUBuy.domain.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Entity
@Component
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_gen")
    private Long id;

    private String description;

    @ManyToOne(targetEntity = Note.class, fetch = FetchType.LAZY)
    private Note note;

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
