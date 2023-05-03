package com.pashtetpashtetovv.canUBuy.repository;

import com.pashtetpashtetovv.canUBuy.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
////http://localhost:8080/h2-console/
public interface NoteRepository extends JpaRepository<Note, Long> {
}
