package com.pashtetpashtetovv.canUBuy.domain.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Component
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_gen")
    private Long id;

    private String title;

    private String description;

    @OneToMany(targetEntity=Line.class, cascade = CascadeType.ALL, mappedBy="note", orphanRemoval = true)
    private Set<Line> lines;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private User owner;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<User> subscribers;

    public Note(){}

    public Note(String title, String description, User owner){
        this.description = description;
        this.title = title;
        this.owner = owner;
    }

    public Note(String title){
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Set<Line> getLines() {
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
