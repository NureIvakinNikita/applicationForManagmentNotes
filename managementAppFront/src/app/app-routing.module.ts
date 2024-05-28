import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { NotesComponent } from './notes/notes.component';
import { NotesListComponent } from './notes-list/notes-list.component';
import { NoteResolver } from './note-resolver';

const routes: Routes = [
  {path: 'header', component: HeaderComponent},
  {path: 'notes/create', component: NotesComponent, resolve: {note: NoteResolver}},
  {path: 'notes/update', component: NotesComponent, resolve: {note: NoteResolver}},
  {path: 'notes', component: NotesListComponent},
  {path: '', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
