import { Component, OnInit } from '@angular/core';
import { NotesService } from '../notes.service';
import { Note } from '../note.modul';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { getNotes, deleteNote } from '../state/notes/note.actions';
import { NotesState } from '../state/notes/note.reducer';
import { selectAllNotes } from '../state/notes/note.selectors';

@Component({
  selector: 'app-notes-list',
  templateUrl: './notes-list.component.html',
  styleUrls: ['./notes-list.component.scss']
})
export class NotesListComponent implements OnInit {
  notes$: Observable<Note[]>;

  constructor(private store: Store<{ notes: NotesState }>, private router: Router) {
    this.notes$ = store.select(selectAllNotes);
  }

  ngOnInit(): void {
    this.store.dispatch(getNotes());
  }

  deleteNote(noteId: number): void {
    console.log(noteId);
    this.store.dispatch(deleteNote({ noteId }));
  }

  updateNote(noteId: number): void {
    this.router.navigate(['/notes/update', noteId]);
  }
}
