import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, catchError, retry, throwError, of } from 'rxjs';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HealthService } from './health.service';
import { AuthService } from './AuthService';


export interface Dataa{
  request:String;
}

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  dataa:Dataa={
    request:""
  }
  stackVisited = false;
  responseclient: Observable<HttpEvent<any>>;
  requestPath: string;
  constructor(public auth: AuthService, private router: Router, private jwtHelper: JwtHelperService,
    private healthService:HealthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.headers.get('No-Auth') === 'True') {
      return next.handle(request.clone());
    }
    // const exp = this.jwtHelper.decodeToken(this.auth.getToken()).exp;
    // const currentTimestamp= Math.floor(Date.now() / 1000);
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.auth.getToken()}`
      }
    });

    this.responseclient = next.handle(request).pipe(

      retry(1),
      catchError((error: HttpErrorResponse) => {
        this.requestPath=error.url;
        while((this.requestPath.indexOf("/"))>=1 ) {
          this.requestPath = this.requestPath.replace("/","-");
        }

        if (error.status === 401) {
             this.auth.clear();
             this.router.navigate(["/login"]);
             this.healthService.addError(1,this.requestPath).subscribe(d=> {console.log(d)},error => console.log(error));
            // this.healthService.getErrors(1).subscribe(d => console.log(d) );
        } else if (error.status === 500) {
             this.healthService.addError(2,this.requestPath).subscribe(d=> {console.log(d)},error => console.log(error));
        } else if (error.status === 404) {
             this.healthService.addError(3,this.requestPath).subscribe(d=> {console.log(d)},error => console.log(error));
        } else {
             this.healthService.addError(4,this.requestPath).subscribe(d=> {console.log(d)},error => console.log(error));
        }
        return throwError(error);
      })
    );
    return this.responseclient;
  }

}
