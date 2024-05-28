import { Component, OnInit } from '@angular/core';
import { NotesService } from '../notes.service';
import { Note } from '../note.modul';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notes-list',
  templateUrl: './notes-list.component.html',
  styleUrl: './notes-list.component.scss'
})
export class NotesListComponent implements OnInit {

  noteId: number = 0;

  notes: Note[] = [];  

  constructor(private notesService: NotesService,
    private router: Router) { 
      this.getNotes();
  }

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes(): void {
    this.notesService.getNotes().subscribe(
      {
        next: (res: Note[]) => {
          this.notes = res;
          console.log(this.notes);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      }
    );
  }

  deleteNote(noteId: number): void {
    console.log(noteId);
    this.notesService.deleteNote(noteId).subscribe(
      {
        next: (res) => {
          console.log(res);
          this.getNotes();
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      }
    );
  }

  updateNote(noteId: number): void {
    this.router.navigate(['/notes/update', {noteId: noteId}]);
  }

}
