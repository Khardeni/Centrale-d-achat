import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/AuthService';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reset-mail',
  templateUrl: './reset-mail.component.html',
  styleUrls: ['./reset-mail.component.css']
})
export class ResetMailComponent {
  password : any;
  code : any;
  reseteMail : any;
  constructor(private userService: UserService, private authService : AuthService, private router:Router){
    if (this.authService.isAuthenticated())
    this.router.navigate(['/unauthorized']);
  }
  resetMail(){
    this.userService.ResetPwdWithMail(this.code, this.password).subscribe((response) => {
      this.reseteMail = response.body;
      console.log(this.reseteMail.result);
        if (this.reseteMail.result == 0){
          Swal.fire({
            title: 'Reset Error',
            text: 'Invalid or expired code, try reseting again',
            icon: 'error',
            showCancelButton: false,
            confirmButtonText: 'OK'
          });           
          this.router.navigate(['/reset']);
        }
        else {
          Swal.fire({
            title: 'Success',
            text: 'Your password has been updated!',
            icon: 'success',
            showCancelButton: false,
            confirmButtonText: 'OK'
          });
          this.router.navigate(['/login']);
        }
      
      });

  }
}
