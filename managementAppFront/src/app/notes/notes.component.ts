import { Component, OnInit } from '@angular/core';
import { Note } from '../note.modul';
import { NgForm } from '@angular/forms';
import { NotesService } from '../notes.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrl: './notes.component.scss'
})
export class NotesComponent implements OnInit {

  note: Note = {
    noteId: 1,
    title: '',
    description: '',
    creationDate: '',
    changeDate: ''
  }

  constructor(private noteService: NotesService,
    private router: Router,
  ) {

  }

  createNote(noteForm: NgForm): void {
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
  }


  ngOnInit(): void {
  }

}
