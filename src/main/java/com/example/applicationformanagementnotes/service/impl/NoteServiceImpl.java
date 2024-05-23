package com.example.applicationformanagementnotes.service.impl;

import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.repository.NoteRepository;
import com.example.applicationformanagementnotes.service.NoteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    //TO DO: Add custom exceptions for methods

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

    @Override
    public boolean createNote(Note noteDTO) {
        Note note = Note.builder()
                .title(noteDTO.getTitle())
                .description(noteDTO.getDescription())
                .creationDate(LocalDate.now())
                .build();
        try {
            save(note);
        } catch (Exception e) {
            log.info("Error in saving note.");
            return false;
        }
        return true;
    }

    @Override
    public boolean updateNote(Integer id) {
        return false;
    }

    @Transactional
    public void save(Note note) {
        noteRepository.save(note);
    }
}
