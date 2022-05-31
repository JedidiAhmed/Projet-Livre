import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserModule } from '@angular/platform-browser';

//Data Binding From VIEW To MODULE
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LivresComponent } from './livres/livres.component';
import { AddLivreComponent } from './add-livre/add-livre.component';
import { UpdateLivreComponent } from './update-livre/update-livre.component';

//Me copy
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RechercheParGenreComponent } from './recherche-par-genre/recherche-par-genre.component';
import { RechercheParNomComponent } from './recherche-par-nom/recherche-par-nom.component';
import { RechercheParTitreComponent } from './recherche-par-titre/recherche-par-titre.component';

import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UsersComponent } from './users/users.component';
import { ListeGenreComponent } from './liste-genre/liste-genre.component';
import { SearchFilterPipe } from './search-filter.pipe';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { UpdateGenreComponent } from './update-genre/update-genre.component';
//import { TokenInterceptor } from './services/token.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    LivresComponent,
    AddLivreComponent,
    UpdateLivreComponent,
    RechercheParGenreComponent,
    RechercheParNomComponent,
    RechercheParTitreComponent,
    LoginComponent,
    LogoutComponent,
    ForbiddenComponent,
    LoginComponent,
    LogoutComponent,
    ForbiddenComponent,
    RechercheParGenreComponent,
    RechercheParTitreComponent,
    UpdateUserComponent,
    AddUserComponent,
    UsersComponent,
    ListeGenreComponent,
    SearchFilterPipe,
    UpdateGenreComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //Data Binding From VIEW To MODULE
    FormsModule,
    //Spring Boot API REST
    HttpClientModule,

    ////////////////////
    ReactiveFormsModule,
    NgbModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'})

    
  ],
  providers: [ /*{ provide : HTTP_INTERCEPTORS,
                useClass : TokenInterceptor,
  multi : true}*/],
  bootstrap: [AppComponent]
})
export class AppModule { }
