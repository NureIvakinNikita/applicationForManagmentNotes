package com.example.applicationformanagementnotes.controller;


import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getSpecifiedNote(@PathVariable Integer id){
        Note note = noteService.getNoteById(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
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
