package com.example.applicationformanagementnotes;

import com.example.applicationformanagementnotes.entity.Note;
import com.example.applicationformanagementnotes.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteRepositoryTests {

    @Autowired
    private NoteRepository noteRepository;

    private Note testNote;

    @BeforeEach
    public void initialize() {
        testNote = Note.builder()
                .title("Hello World!!!")
                .description("This is test note")
                .creationDate(LocalDate.now()).build();
        noteRepository.save(testNote);
    }



    @Test
    @Order(1)
    public void saveNoteTest() {
        //given
        Note note = Note.builder()
                .title("Hello World¿For save")
                .description("This is test note")
                .creationDate(LocalDate.now()).build();
        //when
        noteRepository.save(note);
        //then
        Assertions.assertThat(note.getNoteId()).isGreaterThan(1);
    }

    @Test
    @Order(2)
    public void findNoteByIdTest(){

        //when
        Note noteFromDb = noteRepository.findNoteByNoteId(testNote.getNoteId()).get();

        //then
        Assertions.assertThat(noteFromDb.getNoteId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void findAllNotes(){

        //when
        List<Note> notes = noteRepository.findAll();

        //then
        Assertions.assertThat(notes.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateNoteTest(){

        //given
        Note note = noteRepository.findNoteByNoteId(testNote.getNoteId()).get();

        //when
        note.setDescription("1111111111");
        noteRepository.save(note);

        //then
        Assertions.assertThat(note.getDescription()).isEqualTo("1111111111");

    }

    @Test
    @Order(5)
    public void deleteNoteTest() {

        noteRepository.delete(testNote);

        Assertions.assertThat(noteRepository.findNoteByNoteId(1).isPresent()).isEqualTo(false);
    }

}
