package com.pashtetpashtetovv.canUBuy.domain.builder;

import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.domain.model.User;

public class NoteBuilder {

    private String title;

    private String description;

    private User owner;

    public NoteBuilder() {
    }

    public NoteBuilder title(String title){
        this.title = title;
        return this;
    }

        public NoteBuilder description(String descr){
            this.description = descr;
            return this;
        }

    public NoteBuilder owner(User owner){
        this.owner = owner;
        return this;
    }

    public Note build(){
        return new Note(title, description, owner);
    }

}
