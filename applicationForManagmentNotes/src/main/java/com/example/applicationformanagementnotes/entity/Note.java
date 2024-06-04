package com.example.applicationformanagementnotes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "note", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;

    @NotNull(message = "Title must contain at least one symbol.")
    @Size(min = 1, max = 100, message = "Title must be less than 100 symbols.")
    private String title;

    @NotNull(message = "Description must contain at least five symbols.")
    @Size(min = 5, max = 300, message = "Description must be less than 300 symbols.")
    private String description;


    private LocalDate creationDate;

    private LocalDate changeDate;
}
