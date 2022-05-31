import { LivreService } from './../services/livre.service';
import { Component, OnInit } from '@angular/core';
import { Livre } from './../model/livre.model';
import { Genre } from './../model/genre.model';
//import { AuthService } from '../services/auth.service';
/*import { Router } from '@angular/router';*/

@Component({
  selector: 'app-recherche-par-nom',
  templateUrl: './recherche-par-nom.component.html',
  styleUrls: ['./recherche-par-nom.component.css']
})
export class RechercheParNomComponent implements OnInit {

  //livresRecherche : Livre[];
  livres !: Livre[];
  genres !: Genre[];
  ch !: string;

  constructor(private livreService : LivreService
              //,public authService: AuthService
              /*,private router: Router*/) { }

  ngOnInit(): void {
                    //this.livres = this.livreService.listeLivres();
                    this.livres = [];
  }



  //API REST
  onChange() {
    //console.log(this.idGenre);
    this.livreService.rechercherParNom(this.ch).subscribe(livs => {
      console.log(livs);
      this.livres = livs;
      });
    } 

}
