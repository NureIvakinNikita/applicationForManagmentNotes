package com.example.applicationformanagmentnotes.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String title;

    private String description;
}
