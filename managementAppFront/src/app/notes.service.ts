import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Note } from './note.modul';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotesService {

  constructor(private http: HttpClient) { }

  api = "http://localhost:8080"

  public createNote(note: Note): Observable<boolean> {
    return this.http.post<boolean>(`${this.api}/api/notes`, note);
  }
}
