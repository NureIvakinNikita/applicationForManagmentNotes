package com.example.applicationformanagementnotes.service;


import com.example.applicationformanagementnotes.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    Note getNoteById(Integer id);

    Note createNote(Note note);

    boolean updateNote(Integer id, Note noteDTO);

    boolean deleteNote(Integer id);

}
