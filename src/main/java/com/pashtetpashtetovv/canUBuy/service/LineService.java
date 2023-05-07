package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.model.Line;
import com.pashtetpashtetovv.canUBuy.domain.model.Note;
import com.pashtetpashtetovv.canUBuy.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepo;

    public Line getById(Long id){
        return lineRepo.findById(id).get();
    }

    public List<Line> findAll(){
        return lineRepo.findAll();
    }

    public Line create(Line line){
        return lineRepo.save(line);
    }

    public List<Line> findByNote(Note note){
        return lineRepo.findByNote(note);
    }

    public void delete(Long id){
        lineRepo.deleteById(id);
    }
}

