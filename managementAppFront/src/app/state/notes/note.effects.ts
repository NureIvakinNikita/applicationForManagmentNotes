import { Injectable } from "@angular/core";
import { mergeMap, map, catchError, of } from "rxjs";
import { NotesService } from "../../notes.service";
import * as NotesActions from './note.actions'
import { Actions, createEffect, ofType } from '@ngrx/effects';
0

@Injectable()
export class NotesEffects {
  constructor(
    private actions$: Actions,
    private notesService: NotesService
  ) {}

  loadNotes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.getNotes),
      mergeMap(() => this.notesService.getNotes().pipe(
        map(notes => NotesActions.getNotesSuccess({ notes })),
        catchError(error => of(NotesActions.getNotesFail({ error })))
      ))
    )
  );

  createNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.createNote),
      mergeMap(action => this.notesService.createNote(action.note).pipe(
        map(() => NotesActions.createNoteSuccess({ note: action.note })),
        catchError(error => of(NotesActions.createNoteFail({ error })))
      ))
    )
  );

  updateNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.updateNote),
      mergeMap(action => this.notesService.updateNote(action.note.noteId, action.note).pipe(
        map(() => NotesActions.updateNoteSuccess({ note: action.note })),
        catchError(error => of(NotesActions.updateNoteFail({ error })))
      ))
    )
  );

  deleteNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.deleteNote),
      mergeMap(action => this.notesService.deleteNote(action.noteId).pipe(
        map(() => NotesActions.deleteNoteSuccess({ noteId: action.noteId })),
        catchError(error => of(NotesActions.deleteNoteFail({ error })))
      ))
    )
  );
}
