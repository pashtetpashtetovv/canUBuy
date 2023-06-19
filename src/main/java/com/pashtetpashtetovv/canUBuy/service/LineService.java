package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.model.Line;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.repository.LineRepository;
import com.pashtetpashtetovv.canUBuy.utils.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    private final LineRepository lineRepo;

    private final NoteService noteService;

    public LineService(LineRepository lineRepo, NoteService noteService) {
        this.lineRepo = lineRepo;
        this.noteService = noteService;
    }

    public Line getById(Long id){
        return lineRepo.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException(String.format("Line with id: %d not found", id))
                );
    }

    public List<Line> findAll(){
        return lineRepo.findAll();
    }

    public Line create(Line line){
        return lineRepo.save(line);
    }

    public Line createAndSetNote(Line line, Long noteID){
        line.setNote(noteService.findById(noteID));
        return lineRepo.save(line);
    }

    public List<Line> findByNote(Note note){
        return lineRepo.findByNote(note);
    }

    public void delete(Long id){
        lineRepo.deleteById(id);
    }
}

