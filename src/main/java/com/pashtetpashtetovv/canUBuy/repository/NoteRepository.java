package com.pashtetpashtetovv.canUBuy.repository;

import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.owner.login = ?1")
    List<Note> findByOwner_Login(@NonNull String login);

}
