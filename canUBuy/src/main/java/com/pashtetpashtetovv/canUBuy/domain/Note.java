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

    private String title;

    private String description;

    @OneToMany(targetEntity=Line.class, cascade = CascadeType.ALL, mappedBy="note", orphanRemoval = true)
    private List<Line> lines;

    public Note(){}

    public Note(String title, String description){
        this.description = description;
        this.title = title;
    }

    public Note(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Line> getLines() {
        return lines;
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
