/*import { AuthService } from './auth.service';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {


  constructor(private authService: AuthService) { }
  intercept(request: HttpRequest<unknown>, next: HttpHandler):
    Observable<HttpEvent<unknown>> {
    let jwt = this.authService.getToken();
    let reqWithToken = request.clone({
      setHeaders: { Authorization: "Bearer " + jwt }
    })
    return next.handle(reqWithToken);
  }*/
  /*constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request);
  }*/
//}
