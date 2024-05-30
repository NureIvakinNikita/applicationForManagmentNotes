package com.example.applicationformanagementnotes.service.impl;

import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.exceptions.NoteCreationException;
import com.example.applicationformanagementnotes.exceptions.NoteDeletionException;
import com.example.applicationformanagementnotes.exceptions.NoteNotFoundException;
import com.example.applicationformanagementnotes.exceptions.NoteUpdateException;
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
            log.error("There aren't any notes with id: " + id);
            throw new NoteNotFoundException("Error there aren't any notes with id");
        }
    }

    @Override
    public Note createNote(Note noteDTO) {
        Note note = Note.builder()
                .title(noteDTO.getTitle())
                .description(noteDTO.getDescription())
                .creationDate(LocalDate.now())
                .build();
        try {
            return save(note);
        } catch (Exception e) {
            log.error("Error in saving note.");
            throw new NoteCreationException("Error in saving note, maybe note with such title exists");
        }

    }

    @Override
    public boolean updateNote(Integer id, Note noteDTO) {
        Note note = getNoteById(id);
        note.setTitle(noteDTO.getTitle());
        note.setDescription(noteDTO.getDescription());
        note.setChangeDate(LocalDate.now());

        try {
            save(note);
        } catch (Exception e) {
            throw new NoteUpdateException("Error updating note, maybe note with such title exists");
        }

        return true;
    }

    @Override
    public boolean deleteNote(Integer id) {
        Note note = getNoteById(id);

        try {
            noteRepository.delete(note);
        } catch (Exception e) {
            log.error("Error deleting note with id " + id, e);
            throw new NoteDeletionException("Error deleting note with id " + id);
        }

        return true;
    }

    @Transactional
    public Note save(Note note) {
        return noteRepository.save(note);
    }
}

