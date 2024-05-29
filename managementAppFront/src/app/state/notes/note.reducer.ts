import { createReducer, on } from "@ngrx/store";
import { Note } from "../../note.modul";
import * as NotesActions from './note.actions'

export interface NotesState {
    notes: Note[];
    error: any;
}
  
export const initialState: NotesState = {
    notes: [],
    error: null
};

export const notesReducer = createReducer(
    initialState,
    on(NotesActions.getNotesSuccess, (state, { notes }) => ({
        ...state,
        notes,
        error: null,
    })),
    on(NotesActions.getNotesFail, (state, { error }) => ({
        ...state,
        error,
    })),
    on(NotesActions.createNoteSuccess, (state, { note }) => ({
        ...state,
        notes: [...state.notes, note],
        error: null,
    })),
    on(NotesActions.createNoteFail, (state, { error }) => ({
        ...state,
        error,
    })),
    on(NotesActions.updateNoteSuccess, (state, { note }) => ({
      ...state,
      notes: state.notes.map(n => n.noteId === note.noteId ? note : n),
      error: null
    })),
    on(NotesActions.updateNoteFail, (state, { error }) => ({
      ...state,
      error
    })),
    on(NotesActions.deleteNoteSuccess, (state, { noteId }) => ({
      ...state,
      notes: state.notes.filter(note => note.noteId !== noteId),
      error: null
    })),
    on(NotesActions.deleteNoteFail, (state, { error }) => ({
      ...state,
      error
    }))
);