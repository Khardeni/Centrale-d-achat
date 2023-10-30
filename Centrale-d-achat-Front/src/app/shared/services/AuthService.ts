import { Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';
import decode, { InvalidTokenError } from 'jwt-decode';
import {JwtHelperService} from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './user.service';
@Injectable()
export class AuthService {
  //Get roles from local storage

  public currentUser: Observable<User>;
  public loginstatus:boolean = true;

  private currentUserSubject: BehaviorSubject<User>;

  constructor(private jwtHelper: JwtHelperService){
  }


  //Get token frol local storage
  
  public getToken(): string {
    return localStorage.getItem('token');
  }


  //Check if the user is still logged in

  public isAuthenticated(): boolean {
    return this.getRoles() && this.getToken()!== null ;
    }

    
  //Set roles in local storage
    
  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }
    
  public setUserType(role:string ) {
    localStorage.setItem('user', role);
  }
  public setUsername(userName: any) {
    localStorage.setItem('username', userName);
  }
  public setFullname(fullname: any) {
    localStorage.setItem('fullname', fullname);
  }

  //Get roles from local storage

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles'));
  }

  //Set Token in local storage

  public setToken(jwtToken: string) {
    localStorage.setItem('token', jwtToken);
  }

    //Set Email in local storage

public setEmail(mail : string){
  localStorage.setItem('email', mail);
}

    //get Email from local storage

public getEmail(): string{
  return localStorage.getItem('email');
}

public setPhone(phone : string){
  localStorage.setItem('phone', phone);
}

    //get Email from local storage

public getPhone(): string{
  return localStorage.getItem('phone');
}

  //Clear local storage for logout

  public clear() {
    localStorage.clear();
  }


}
