package com.example.applicationformanagementnotes;

import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.exceptions.NoteCreationException;
import com.example.applicationformanagementnotes.exceptions.NoteDeletionException;
import com.example.applicationformanagementnotes.exceptions.NoteNotFoundException;
import com.example.applicationformanagementnotes.exceptions.NoteUpdateException;
import com.example.applicationformanagementnotes.repository.NoteRepository;
import com.example.applicationformanagementnotes.service.impl.NoteServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTests {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    private Note note;

    @BeforeEach
    private void initialize(){
        note = Note.builder()
                .noteId(1)
                .title("Test Title")
                .description("Test Description")
                .creationDate(LocalDate.now())
                .build();
    }


    @Test
    public void getAllNotesTestShouldReturnListOfNotes(){
        when(noteRepository.findAll()).thenReturn(Collections.singletonList(note));

        assertThat(noteService.getAllNotes()).hasSize(1).contains(note);
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void getNoteByIdTestShouldReturnNote(){
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.of(note));

        assertThat(noteService.getNoteById(1)).isEqualTo(note);
        verify(noteRepository, times(1)).findNoteByNoteId(1);
    }

    @Test
    public void getNoteById_ShouldThrowNoteNotFoundException() {
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> noteService.getNoteById(1))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessageContaining("Error there aren't any notes with id");
        verify(noteRepository, times(1)).findNoteByNoteId(1);
    }

    @Test
    public void createNoteShouldReturnCreatedNote(){
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note newNote = Note.builder()
                .title("Test Title")
                .description("Test Title").build();

        assertThat(noteService.createNote(newNote)).isEqualTo(note);
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void createNoteShouldThrowNoteCreationException() {
        when(noteRepository.save(any(Note.class))).thenThrow(new RuntimeException());

        Note newNote = new Note();
        newNote.setTitle("Test Title");
        newNote.setDescription("Test Description");

        assertThatThrownBy(() -> noteService.createNote(newNote))
                .isInstanceOf(NoteCreationException.class)
                .hasMessageContaining("Error in saving note, maybe note with such title exists");
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void updateNoteShouldReturnTrue() {
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note newNoteData = Note.builder()
                .title("Updated Title")
                .description("Updated Description").build();

        assertThat(noteService.updateNote(1, newNoteData)).isTrue();
        verify(noteRepository, times(1)).findNoteByNoteId(1);
        verify(noteRepository, times(1)).save(any(Note.class));

    }

    @Test
    public void updateNoteShouldThrowNoteUpdateException() {
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenThrow(new RuntimeException());

        Note newNoteData = Note.builder()
                .title("Updated Title")
                .description("Updated Description").build();

        assertThatThrownBy(() -> noteService.updateNote(1, newNoteData))
                .isInstanceOf(NoteUpdateException.class)
                .hasMessageContaining("Error updating note, maybe note with such title exists");
        verify(noteRepository, times(1)).findNoteByNoteId(1);
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void deleteNote_ShouldReturnTrue() {
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.of(note));

        assertThat(noteService.deleteNote(1)).isTrue();
        verify(noteRepository, times(1)).findNoteByNoteId(1);
        verify(noteRepository, times(1)).delete(note);
    }

    @Test
    public void deleteNote_ShouldThrowNoteDeletionException() {
        when(noteRepository.findNoteByNoteId(1)).thenReturn(Optional.of(note));
        doThrow(new RuntimeException()).when(noteRepository).delete(note);

        assertThatThrownBy(() -> noteService.deleteNote(1))
                .isInstanceOf(NoteDeletionException.class)
                .hasMessageContaining("Error deleting note with id 1");
        verify(noteRepository, times(1)).findNoteByNoteId(1);
        verify(noteRepository, times(1)).delete(note);
    }



}
