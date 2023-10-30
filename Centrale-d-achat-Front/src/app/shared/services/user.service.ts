import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './AuthService';
import { throwError } from 'rxjs';
import { error } from 'jquery';


export interface Role {
  roleName: string;
  roleDescription:string;
}

export interface User{
  userName:string,
  userPassword:string
  role: Role[];
}
export interface jwtresponse{
  user:User,
  jwtToken:string
}

export interface data{
email : email,
phone : phone
}
export interface email{
  email : string,
  }
  export interface phone{
    phone : string,
    }
@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly baseUrl='http://localhost:8099';

  role : Role = {
    roleName :"",
    roleDescription :""
  }
  user : User =
    {
      userName: "",
      userPassword:"",
      role: [this.role]
    };

    jwtres : jwtresponse = 
    {
      user: this.user,
      jwtToken:""
    };
    email : email = {
      email : ""
    };

    phone : phone = {
      phone : ""
    };
    data : data = 
    {
      email:this.email,
      phone: this.phone
    };
    resetMail : any = 
    {
      email:"",
      code: "",
      password: ""
    };
    resetSMS : any = 
    {
      phone:"",
      code: "",
      password: ""
    };


  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

   httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    })
};

  constructor(private httpClient:HttpClient, private authService: AuthService) {
  }

  //Login

  authenticate(userName: string, userPassword: string) {
    this.user.userName=userName;
    this.user.userPassword=userPassword;
    const url = `${this.baseUrl}/authenticate`;
      return this.httpClient.post(url,this.user,{headers : this.requestHeader,})
  }


//Clear the local storage

  logout() {
    return this.authService.clear();
  }


//Register new user

  addUser(user: any) {
    return this.httpClient.post(`${this.baseUrl}/registerNewUser`, user,{headers : this.requestHeader,});

  }


//Display users

  showuser() {
    return this.httpClient.get(`${this.baseUrl}/users`);
  }


//Change User role

  addRoleToUser(roleName: string, userName: string) {
    return this.httpClient.put(`${this.baseUrl}/addRole/${roleName}/${userName}`,null);
  }


  //Delete User

   deleteUser(useraName : string) {
    return this.httpClient.delete(`${this.baseUrl}/delete/${useraName}`,{observe: 'response'});
  }



//Compares a list of user roles to a list of allowed roles

 roleMatch(allowedRoles): boolean {
  let isMatch = false;
  const userRoles: any = this.authService.getRoles();

  if (userRoles != null && userRoles) {
    for (let i = 0; i < userRoles.length; i++) {
      for (let j = 0; j < allowedRoles.length; j++) {
        if (userRoles[i].roleName === allowedRoles[j]) {
          isMatch = true;
        } 
      }
    }  return isMatch;
  } else return isMatch;
}

//Verify user by email

verifyAccount(activateToken){
  return this.httpClient.put(`${this.baseUrl}/activate/${activateToken}`,null,{headers : this.requestHeader,observe: 'response'});
}

//Check pwd

checkEmail(resetOption : string, data : data){
  this.data.email.email = data.email.email;
  this.data.phone.phone = data.phone.phone;
  if (resetOption === "email")
  {
      return this.httpClient.post(`${this.baseUrl}/checkEmail`,data.email,{headers : this.requestHeader,observe: 'response'})
  }
  else if (resetOption === "sms")
  {
    return this.httpClient.post(`${this.baseUrl}/checkSMS`,data.phone,{headers : this.requestHeader,observe: 'response'})
  }
  else return null;
}

//Reset pwd

ResetPwdWithMail(code : string, password : string){
  this.resetMail.email = this.authService.getEmail();
  this.resetMail.code = code;
  this.resetMail.password = password;
  console.log(this.resetMail);
  return this.httpClient.post(`${this.baseUrl}/resetPassword`,this.resetMail,{headers : this.requestHeader,observe: 'response'})


}

ResetPwdWithSMS(code : string, password : string){
  this.resetSMS.phone = this.authService.getPhone();
  this.resetSMS.code = code;
  this.resetSMS.password = password;
  return this.httpClient.post(`${this.baseUrl}/resetPasswordSMS`,this.resetSMS,{headers : this.requestHeader,observe: 'response'})


}


}