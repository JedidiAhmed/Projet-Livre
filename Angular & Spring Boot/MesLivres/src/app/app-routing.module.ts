import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

//Guard
import { LivreGuard } from './livre.guard';
import { AdminGuard } from './admin.guard';

import { LivresComponent } from './livres/livres.component';
import { AddLivreComponent } from './add-livre/add-livre.component';
import { UpdateLivreComponent } from './update-livre/update-livre.component'

//Recherche
import { RechercheParGenreComponent } from './recherche-par-genre/recherche-par-genre.component';
import { RechercheParTitreComponent } from './recherche-par-titre/recherche-par-titre.component';
import { RechercheParNomComponent } from './recherche-par-nom/recherche-par-nom.component';

//User
import { UsersComponent } from './users/users.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';

import { ListeGenreComponent } from './liste-genre/liste-genre.component'; 

const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent,  canActivate:[LivreGuard]},

  //API REST
  {path: "users", component : UsersComponent , canActivate:[AdminGuard]},
  {path: "add-user", component : AddUserComponent , canActivate:[AdminGuard]},
  {path: "update-user/:id", component : UpdateUserComponent , canActivate:[AdminGuard]},
  
  //Forbidden non Admin
  {path: 'app-forbidden', component: ForbiddenComponent},

  //API REST
  {path: "livres", component : LivresComponent,  canActivate:[LivreGuard]},
  {path: "add-livre", component : AddLivreComponent,  canActivate:[AdminGuard]},
  {path: "updateLivre/:id", component: UpdateLivreComponent,  canActivate:[LivreGuard]},

  //Search
  {path: "rechercheParGenre", component : RechercheParGenreComponent,  canActivate:[LivreGuard]},
  {path: "rechercheParTitre", component : RechercheParTitreComponent,  canActivate:[LivreGuard]},
  {path: "rechercheParNom", component : RechercheParNomComponent,  canActivate:[LivreGuard]},

  //API REST
  {path: "genres", component : ListeGenreComponent,  canActivate:[AdminGuard]},

  //Path
  {path: "", redirectTo: "livres", pathMatch: "full"},
  //Othterwise redirect to ColorGame
  { path: "*", redirectTo: "livres"}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
