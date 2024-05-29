import { createFeatureSelector, createSelector } from "@ngrx/store";
import { NotesState } from "./note.reducer";

export const selectNotesState = createFeatureSelector<NotesState>('notes');

export const selectAllNotes = createSelector(
    selectNotesState,
    (state: NotesState) => state.notes
);

export const selectNoteById = (noteId: number) => createSelector(
    selectAllNotes,
    (notes) => notes.find(note => note.noteId === noteId)
);