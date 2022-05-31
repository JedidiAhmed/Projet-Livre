import { Genre } from './../model/genre.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


@Component({
  selector: 'app-update-genre',
  templateUrl: './update-genre.component.html',
  styleUrls: ['./update-genre.component.css']
})
export class UpdateGenreComponent implements OnInit {

  //genreUpdated:Genre = {"idGen":0,"nomGen":"", "descriptionGen":""};

  @Input()
  genre! : Genre;

  /*@Input()
  data! : string;*/

  @Output() 
  genreUpdated = new EventEmitter<Genre>();

  @Input()
  ajout!:boolean;


  constructor() { }

  ngOnInit(): void {
  }


  saveGenre(){
    this.genreUpdated.emit(this.genre);
    }

}
