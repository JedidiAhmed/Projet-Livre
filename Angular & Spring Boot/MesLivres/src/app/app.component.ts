import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MesLivres';





  //Username
  constructor(public authService: AuthService,
   private router : Router){}
  ngOnInit () {
  let isloggedin : string | null;
  let loggedUser : string | null;
  let AdminOrUser : string | null;
  isloggedin = localStorage.getItem('isloggedIn');
  loggedUser = localStorage.getItem('loggedUser');
  AdminOrUser = localStorage.getItem('AdminOrUser');
  if (isloggedin!="true" || !loggedUser || !AdminOrUser )
  this.router.navigate(['/login']);
  else
  this.authService.setLoggedUserFromLocalStorage(loggedUser , AdminOrUser);






    this.authService.loadToken();
    if (this.authService.getToken() == null /*|| this.authService.isTokenExpired()*/) {
      this.router.navigate(['/login']);
      this.authService.logout();
    }
  }
  
  /*onLogout(){
  this.authService.logout();
  }*/
}
