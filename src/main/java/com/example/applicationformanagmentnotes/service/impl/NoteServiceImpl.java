package com.example.applicationformanagmentnotes.service.impl;

import com.example.applicationformanagmentnotes.repository.NoteRepository;
import com.example.applicationformanagmentnotes.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
}
