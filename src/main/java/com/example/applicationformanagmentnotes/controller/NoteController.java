package com.example.applicationformanagmentnotes.controller;


import com.example.applicationformanagmentnotes.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoteController {

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(){
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getSpecifiedNote(@PathVariable Integer id){
        return new ResponseEntity<>(new Note(), HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<Boolean> createNote(@RequestBody Note note){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Boolean> updateNote(@PathVariable Integer id){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Boolean> deleteNote(@PathVariable Integer id){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
