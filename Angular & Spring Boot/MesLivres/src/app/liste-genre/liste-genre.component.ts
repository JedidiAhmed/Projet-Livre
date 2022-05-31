import { Genre } from './../model/genre.model';
import { LivreService } from './../services/livre.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-liste-genre',
  templateUrl: './liste-genre.component.html',
  styleUrls: ['./liste-genre.component.css']
})
export class ListeGenreComponent implements OnInit {

  genres! : Genre[];
  gen! : Genre;

  updatedGen:Genre = {"idGen":0,"nomGen":"", "descriptionGen":""};

  ajout:boolean=true;
  

  constructor(private livreService : LivreService ) { }

  ngOnInit(): void {
      this.livreService.listeGenres().
      subscribe(gens => {this.genres = gens;
      console.log(gens);
      });
    
    this.chargerGenres();
  }

  //API rest subscribe LIVRE
  chargerGenres(){
    //API rest subscribe LIVRE
    this.livreService.listeGenres().subscribe(gens => {
      //console.log(gens);
      this.genres = gens;
      });
  }


  genreUpdated(cat:Genre){
    //console.log("Cat updated event",cat);
    this.livreService.ajouterGenre(cat).
     subscribe( ()=> this.chargerGenres());
    }


    updateGen(gen:Genre) {
      this.updatedGen =gen;
      this.ajout=false;
      }
    
  /*genreDelete(gen : Genre){
    let conf = confirm("Etes-vous sûr ?");
      if (conf)
    this.livreService.supprimerGenre(gen.idGen);
  }*/



    //API REST
    genreDelete(gen: Genre) {
      let conf = confirm("Etes-vous sûr ?");
      if (conf)
        this.livreService.supprimerGenre(gen.idGen).subscribe(() => {
          console.log("Genre supprimé");
          this.SuprimerGenreDuTableau(gen);
        });
        /*this.router.navigate(['livres']).then(() => {
        window.location.reload();
        });*/
    }
  
    SuprimerGenreDuTableau(gen: Genre) {
      this.genres.forEach((cur, index) => {
        if (gen.idGen === cur.idGen) {
          this.genres.splice(index, 1);
        }
      });
    }

}
