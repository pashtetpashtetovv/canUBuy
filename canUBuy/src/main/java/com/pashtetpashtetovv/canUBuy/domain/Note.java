package com.pashtetpashtetovv.canUBuy.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Component
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StringBuffer title;

    private StringBuffer description;

    @OneToMany(targetEntity=Line.class, cascade = CascadeType.ALL, mappedBy="note", fetch=FetchType.EAGER)
    private List<Line> lines;

    public Note(){}

    public Note(StringBuffer title, StringBuffer description){
        this.description = description;
        this.title = title;
    }

    public Note(StringBuffer title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public StringBuffer getDescription() {
        return description;
    }

    public void setDescription(StringBuffer description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StringBuffer getTitle() {
        return title;
    }

    public void setTitle(StringBuffer title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title);
    }

    @Override
    public String toString() {
        return String.format("Note: id: %d, title: %s, description: %s", this.id, this.title, this.description);
    }
}
