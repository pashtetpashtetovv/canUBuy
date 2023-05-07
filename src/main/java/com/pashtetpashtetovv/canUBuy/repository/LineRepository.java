package com.pashtetpashtetovv.canUBuy.repository;

import com.pashtetpashtetovv.canUBuy.domain.model.Line;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    List<Line> findByNote(Note note);
}
