import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/AuthService';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  roles = [ 'USER', 'ADMIN', 'FOURNISSEUR']
  user = {
    userName: "",
    userPassword: "",
    userFirstName: "",
    userLastName: "",
    userCin: "",
    userEmail: "",
    userAdress: "",
    userPhone : ""
  };

  constructor(private userService:UserService, private router:Router, private authService: AuthService) {
    if (this.authService.isAuthenticated())
    this.router.navigate(['/unauthorized']);
  }
  onSubmit() {
    this.userService.addUser(this.user).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.log(error);
        alert('Something went wrong, Please try again.');
      }
    );
  }

}
