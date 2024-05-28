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

  public getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.api}/api/notes`)
  }

  public deleteNote(noteId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.api}/api/notes/${noteId}`)
  }

  public getNoteById(noteId: number): Observable<Note> {
    return this.http.get<Note>(`${this.api}/api/notes/${noteId}`);
  }

  public updateNote(noteId: number, note: Note): Observable<boolean> {
    return this.http.put<boolean>(`${this.api}/api/notes/${noteId}`, note)
  }
}
