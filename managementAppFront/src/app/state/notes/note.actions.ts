import { createAction, props } from "@ngrx/store";
import { Note } from "../../note.modul";

export const CREATE_NOTE='[Note Component] Create Note';
export const CREATE_NOTE_SUCCESS='[Note Component] Create Note Success';
export const CREATE_NOTE_FAIL='[Note Component] Create Note Fail';
export const DELET_NOTE='[Note Component] Delete Note';
export const DELET_NOTE_SUCCESS='[Note Component] Delete Note Success';
export const DELET_NOTE_FAIL='[Note Component] Delete Note Fail';
export const UPDATE_NOTE='[Note Component] Update Note';
export const UPDATE_NOTE_SUCCESS='[Note Component] Update Note Success';
export const UPDATE_NOTE_FAIL='[Note Component] Update Note Fail';
export const GET_NOTES='[Note Component] Get Notes';
export const GET_NOTES_SUCCESS='[Note Component] Get Notes Success';
export const GET_NOTES_FAIL='[Note Component] Get Notes Fail';

export const getNotes = createAction(GET_NOTES);
export const getNotesSuccess = createAction(GET_NOTES_SUCCESS, props<{notes: Note[]}>());
export const getNotesFail = createAction(GET_NOTES_FAIL, props<{error: any}>());

export const createNote = createAction(CREATE_NOTE, props<{note: Note}>());
export const createNoteSuccess = createAction(CREATE_NOTE_SUCCESS, props<{note: Note}>());
export const createNoteFail = createAction(CREATE_NOTE_FAIL, props<{error: any}>());

export const deleteNote = createAction(DELET_NOTE, props<{noteId: number}>());
export const deleteNoteSuccess = createAction(DELET_NOTE_SUCCESS, props<{noteId: number}>());
export const deleteNoteFail = createAction(DELET_NOTE_FAIL, props<{error: any}>());


export const updateNote = createAction(UPDATE_NOTE, props<{note: Note}>());
export const updateNoteSuccess = createAction(UPDATE_NOTE_SUCCESS, props<{note: Note}>());
export const updateNoteFail = createAction(UPDATE_NOTE_FAIL, props<{error: any}>());
