package com.example.applicationformanagementnotes.service.impl;

import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.repository.NoteRepository;
import com.example.applicationformanagementnotes.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Integer id) {
        Optional<Note> tempNote = noteRepository.findNoteByNoteId(id);
        if (tempNote.isPresent()){
            return tempNote.get();
        } else {
            log.info("There aren't any notes with id: " + id);
            return null;
        }
    }
}
