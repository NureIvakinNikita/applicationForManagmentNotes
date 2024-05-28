import { Component, OnInit } from '@angular/core';
import { Note } from '../note.modul';
import { NgForm } from '@angular/forms';
import { NotesService } from '../notes.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrl: './notes.component.scss'
})
export class NotesComponent implements OnInit {
[x: string]: any;

  isCreateNote: boolean = true;

  note: Note = { 
    title: '', 
    description: '', 
    creationDate: '', 
    changeDate: '', 
    noteId: 0 
  };

  constructor(private noteService: NotesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {

  }

  createNote(noteForm: NgForm): void {
    if (this.isCreateNote) {
      this.noteService.createNote(this.note).subscribe(
        {
          next: (res: boolean) => {
            console.log(res);
            noteForm.reset();
            this.router.navigate(['/notes']);
          },
          error: (err: HttpErrorResponse) => {
            console.log(err);
          }
        }
      );
    } else {
      this.noteService.updateNote(this.note.noteId, this.note).subscribe(
        {
          next: (res: boolean) => {
            this.router.navigate(['/notes']);
          },
          error: (err: HttpErrorResponse) => {
            console.log(err);
          }
        }
      );
    }
  }


  ngOnInit(): void {
    this.note = this.activatedRoute.snapshot.data['note'];
    console.log(this.note);
    if (this.note && this.note.noteId > 0) {
      this.isCreateNote = false;
    } else {
      
      this.isCreateNote = true;
    }
  }

}
