import { Injectable } from "@angular/core";
import { mergeMap, map, catchError, of, tap } from "rxjs";
import { NotesService } from "../../notes.service";
import * as NotesActions from './note.actions'
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Router } from "@angular/router";


@Injectable()
export class NotesEffects {
  constructor(
    private actions$: Actions,
    private notesService: NotesService,
    private router: Router
  ) {}

  loadNotes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.getNotes),
      mergeMap(() => this.notesService.getNotes().pipe(
        map(notes => NotesActions.getNotesSuccess({ notes })),
        catchError(error => of(NotesActions.getNotesFail({ error: error.error.message })))
      ))
    )
  );

  createNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.createNote),
      mergeMap(action => this.notesService.createNote(action.note).pipe(
        map(() => NotesActions.createNoteSuccess({ note: action.note })),
        tap(() => {
          this.router.navigate(['/notes']);
        }),
        mergeMap(() => [NotesActions.getNotes()]), 
        catchError(error => of(NotesActions.createNoteFail({ error: error.error.message })))
      ))
    )
  );

  updateNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.updateNote),
      mergeMap(action => this.notesService.updateNote(action.note.noteId, action.note).pipe(
        map(() => NotesActions.updateNoteSuccess({ note: action.note })),
        tap(() => {
          this.router.navigate(['/notes']);
        }),
        mergeMap(() => [NotesActions.getNotes()]), 
        catchError(error => of(NotesActions.updateNoteFail({ error: error.error.message })))
      ))
    )
  );

  deleteNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NotesActions.deleteNote),
      mergeMap(action => this.notesService.deleteNote(action.noteId).pipe(
        map(() => NotesActions.deleteNoteSuccess({ noteId: action.noteId })),
        mergeMap(() => [NotesActions.getNotes()]), 
        catchError(error => of(NotesActions.deleteNoteFail({ error: error.error.message })))
      ))
    )
  );
}