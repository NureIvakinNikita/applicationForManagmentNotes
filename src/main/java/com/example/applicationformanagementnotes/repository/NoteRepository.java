package com.example.applicationformanagementnotes.repository;

import com.example.applicationformanagementnotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    Optional<Note> findNoteByNoteId(Integer noteId);
}
