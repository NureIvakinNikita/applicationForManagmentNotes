import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { NotesService } from "./notes.service";
import { inject } from "@angular/core";
import { Observable, of } from "rxjs";
import { Note } from "./note.modul";

export const NoteResolver: ResolveFn<any> = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
    noteService: NotesService = inject(NotesService)
) : Observable<Note> => {
    const noteId = route.paramMap.get("noteId");
    if (noteId) {
        return noteService.getNoteById(Number(noteId));
    } else {
       const note: Note = {
            noteId: 0,
            title: '',
            description: '',
            creationDate: '',
            changeDate: ''
        }
        return of(note);
    }
}