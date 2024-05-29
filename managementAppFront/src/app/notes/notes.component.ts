import { Component, OnInit } from '@angular/core';
import { Note } from '../note.modul';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { NotesService } from '../notes.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { format } from 'date-fns';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { createNote, getNotes, updateNote } from '../state/notes/note.actions';
import { NotesState } from '../state/notes/note.reducer';
import { getNoteError, selectNoteById } from '../state/notes/note.selectors';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {
  isCreateNote: boolean = true;
  note$!: Observable<Note | undefined>;
  noteForm!: FormGroup;
  error: any;

  constructor(
    private fb: FormBuilder,
    private store: Store<{ notes: NotesState }>,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.noteForm = this.fb.group({
      noteId: [0],
      title: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(300)]],
      creationDate: [''],
      changeDate: ['']
    });

    this.store.select(getNoteError).subscribe(item => {
      this.error = item;
      console.log(this.error);
    });

    this.activatedRoute.params.subscribe(params => {
      const noteId = +params['noteId'];
      if (noteId) {
        this.isCreateNote = false;
        this.note$ = this.store.select(selectNoteById(noteId));
        this.note$.subscribe(note => {
          if (note) {
            this.noteForm.patchValue(note); // Patch values to the form
          }
        });
      } else {
        this.isCreateNote = true;
      }
    });
  }

  goToNotes(): void {
    this.router.navigate(['/notes']);
  }

  createNote(): void {
    if (this.noteForm.invalid) {
      return;
    }
  
    const newNote = {
      ...this.noteForm.value,
      creationDate: this.isCreateNote ? format(new Date(), 'yyyy-MM-dd') : this.noteForm.value.creationDate,
      changeDate: format(new Date(), 'yyyy-MM-dd')
    };
  
    if (this.isCreateNote) {
      this.store.dispatch(createNote({ note: newNote }));
    } else {
      this.store.dispatch(updateNote({ note: newNote }));
    }
    
    if (this.error == null) {
      this.router.navigate(['/notes']).then(() => {
        this.store.dispatch(getNotes()); 
      });
    }
    
  }

  get title() {
    return this.noteForm.get('title');
  }

  get description() {
    return this.noteForm.get('description');
  }
}