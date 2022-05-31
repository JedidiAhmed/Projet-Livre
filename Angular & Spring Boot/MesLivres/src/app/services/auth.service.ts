import { JwtHelperService } from '@auth0/angular-jwt';

//Path
import { apiURLconstuser } from './../config';

import { Injectable } from '@angular/core';

//Route
import { Router } from '@angular/router';
//Models
import { Role } from '../model/role.model';
import { User } from '../model/user.model';

//API REST
import { Observable } from 'rxjs';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

// Important to know
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private helper = new JwtHelperService();
  //API REST
  //apiURL: string = 'http://localhost:8085/userBackEnd/api/all';
  apiURL: string = apiURLconstuser+'user/all';
  //apiURL1: string = 'http://localhost:8085/userBackEnd/role';
  apiURL1: string = apiURLconstuser+'role';
  //add user //Post //Delete
  //apiURL2: string = 'http://localhost:8085/userBackEnd/api/';
  apiURL2: string = apiURLconstuser+'user/';
  //get user   //GET by email
  //apiURL3: string = 'http://localhost:8085/userBackEnd/api/logino';
  apiURL3: string = apiURLconstuser+'user/logino';

  apiURL4: string = apiURLconstuser+'login';
  token !: string;



  /*class User
    iduser !: number;
    username !: string ;
    email !: string ;
    password !: string;
    enabled !: boolean;
    role !: Role;
  */

  //Local Users Data 
  public You !: string[]; //un tableau de chaines de caractères
  users !: User[]; //un tableau de User
  roles !: Role[]; //un tableau de Role


  public loggedUser !: string;
  public isloggedIn: Boolean = false;
  public AdminOrUser !: string;





  constructor(private router: Router,
              //API REST
              private http: HttpClient) {
                            this.roles = [];
                            this.users = [];
              }



/////////////////////////////////////////////////////////////////////////////////

   login(user : User)
   {
   return this.http.post<User>(this.apiURL4, user , {observe:'response'});
   }

  saveToken(jwt: string) {
    localStorage.setItem('jwt', jwt);
    this.token = jwt;
    this.isloggedIn = true;
    this.decodeJWT();
  }

  decodeJWT() {
    if (this.token == undefined)
      return;
    const decodedToken = this.helper.decodeToken(this.token);
    this.roles = decodedToken.roles;
    this.loggedUser = decodedToken.sub;
  }

  loadToken() {
    this.token != localStorage.getItem('jwt');
    this.decodeJWT();
    }

    isTokenExpired(): Boolean
    {
      if (this.token == undefined){
        this.token != localStorage.getItem('jwt');
      }

    return this.helper.isTokenExpired(this.token); 
    }

    getToken():string | null{
      if (this.token == undefined){
        this.token != localStorage.getItem('jwt');
      return localStorage.getItem('jwt') ;}
    return this.token;
    }


  //API REST

  listeUsers(): Observable<User[]> {
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.get<User[]>(this.apiURL, {headers:httpHeaders});
  }

  /*listeUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiURL);
  }*/


  listeRoles(): Observable<Role[]> {
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.get<Role[]>(this.apiURL1, {headers:httpHeaders});
  }

  /*listeRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(this.apiURL1);
  }*/

  ajouterUser(usr: User): Observable<User> {
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.post<User>(this.apiURL2, usr, {headers:httpHeaders});
  }

  /*
    ajouterUser(usr: User): Observable<User> {
    return this.http.post<User>(this.apiURL2, usr, httpOptions);
  }
  */

  consulterRole(id: number): Observable<Role> {
    const url = `${this.apiURL1}/${id}`;
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.get<Role>(url, {headers:httpHeaders});
  }

  /*
  consulterRole(id: number): Observable<Role> {
    const url = `${this.apiURL1}/${id}`;
    return this.http.get<Role>(url);
  }
  */


/////////////////////////////////////////////////////////////////////////////////////////



  role = new Role();

  user !: User;


  //Spring Boot
  
  getUserFromDB(email: string): Observable<User> {
    const urll = `${this.apiURL3}/${email}`;
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.get<User>(urll, {headers:httpHeaders});
  }


  /*getUserFromDB(email: string): Observable<User> {
    const urll = `${this.apiURL3}/${email}`;
    return this.http.get<User>(urll);
  }*/


//////////////////////////////////////////////////////////////////////////////////////////

supprimerUser(id: number) {
  const url = `${this.apiURL2}${id}`;
  let jwt = this.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt});
  return this.http.delete(url, {headers:httpHeaders});
}

/*supprimerUser(id: number) {
  const url = `${this.apiURL2}/${id}`;
  return this.http.delete(url, httpOptions);
}*/


//////////////////////////////////////////////////////////////////////////////////////////

consulterUser(id: number): Observable<User> {
  const url = `${this.apiURL2}${id}`;
  let jwt = this.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt});
  return this.http.get<User>(url, {headers:httpHeaders});
}

  /*consulterUser(id: number): Observable<User> {
    const url = `${this.apiURL2}/${id}`;
    return this.http.get<User>(url);
  }*/


  
   //http://localhost:9999/books/user/me/speed@gmail.com

      consulterUserByEmail(email: string): Observable<User> {
        const url = `${this.apiURL2}me/${email}`;
        let jwt = this.getToken();
        jwt = "Bearer "+jwt;
        let httpHeaders = new HttpHeaders({"Authorization":jwt});
        return this.http.get<User>(url, {headers:httpHeaders});
      }


  updateUser(usr: User): Observable<User> {
    let jwt = this.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt});
    return this.http.put<User>(this.apiURL2, usr, {headers:httpHeaders});
  }


  /*updateUser(usr: User): Observable<User> {
    return this.http.put<User>(this.apiURL2, usr, httpOptions);
  }*/

//////////////////////////////////////////////////////////////////////////////////////////


  //login
  /*SignIn(user :User):Boolean{
    let validUser: Boolean = false;
    this.users.forEach((curUser) => {
    if(user.email === curUser.email && user.password==curUser.password) {
    validUser = true;
    this.loggedUser = curUser.username;
    this.isloggedIn = true;
    localStorage.setItem('loggedUser',this.loggedUser);
    localStorage.setItem('isloggedIn',String(this.isloggedIn));
    }
    });
    return validUser;
    }*/
  SignIn(user: User) {
    this.loggedUser = user.username;
    this.isloggedIn = true;
    this.AdminOrUser = String(user.role.nomRole);
    localStorage.setItem('loggedUser', this.loggedUser);
    localStorage.setItem('isloggedIn', String(this.isloggedIn));
    localStorage.setItem('AdminOrUser', String(user.role.nomRole));
    /*let validUser: Boolean = false;
    this.users.forEach((curUser) => {
      if (user.username === curUser.username && user.password == curUser.password) {
        validUser = true;
        this.loggedUser = curUser.username;
        this.isloggedIn = true;
        this.roles = curUser.roles;
        localStorage.setItem('loggedUser', this.loggedUser);
        localStorage.setItem('isloggedIn', String(this.isloggedIn));
      }
    });
    return validUser;*/
  }
  
  
  
  
  
  logout() {
    this.isloggedIn = false;
    this.loggedUser = "";
    this.loggedUser != undefined;
    //this.loggedUser = undefined!;
    this.AdminOrUser = "";
    this.AdminOrUser != undefined;
    //this.AdminOrUser = undefined!;
  
  
    localStorage.removeItem('loggedUser');
    localStorage.removeItem('AdminOrUser');
    localStorage.setItem('isloggedIn', String(this.isloggedIn));

    this.token= undefined!;
    localStorage.removeItem('jwt');
  
    this.router.navigate(['/login']);
  }
  
  
  isAdmin(): Boolean {
    if (!this.loggedUser) //this.loggedUser == undefiened
      return false;
    return true;//(this.loggedUser.indexOf('A') >-1) ;
    ;
    
    /*this.roles.forEach((curRole) => {
      if (curRole.role == 'ADMIN') {
           admin = true;
      }
    });
    return admin;*/
    /*if (!this.roles) //this.roles== undefiened
      return false;
    return (this.roles.indexOf('ADMIN') > -1);
    ;*/
  }
  
  
  setLoggedUserFromLocalStorage(login: string, rol: string) {
    this.loggedUser = login;
    this.isloggedIn = true;
    this.AdminOrUser = rol;
    /*this.getUserRoles(login);*/
  }

  /*getUserRoles(username: string) {
    this.getUserFromDB(username).subscribe((user: User) => {
      this.roles = user.roles;
    });*/
    /*this.users.forEach((curUser) => {
      if (curUser.username == username) {
        this.roles = curUser.roles;
      }
    });*/
  /*}*/
  
  
  
  isAdminVIP(): Boolean {
    let adminVIP: boolean = false;
    if (!this.AdminOrUser) { return false; }
    if (this.AdminOrUser == "Admin") { adminVIP = true; }
    return adminVIP;
  }



}






//Si je veux ajouter des autre role hors User & Admin
//Je doit utiliser this.roles = [],AdminOrUser

//methode logout
//his.roles = undefined!;


//Tester isAdminVIP changer "Admin" avec autre que ça
//isAdmin():Boolean{
//  if (!this.roles) //this.roles== undefiened
//  return false;
//  return (this.roles.indexOf('ADMIN') >-1);
//  }
  