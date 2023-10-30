import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/AuthService';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reset-sms',
  templateUrl: './reset-sms.component.html',
  styleUrls: ['./reset-sms.component.css']
})
export class ResetSMSComponent {
  password : any;
  code : any;
  resetSMS : any;
  
  constructor(private userService: UserService, private authService : AuthService, private router:Router){
    if (this.authService.isAuthenticated())
    this.router.navigate(['/unauthorized']);
  }
  
  ngOnInit(){
  }

  resetSms(){
    this.userService.ResetPwdWithSMS(this.code, this.password).subscribe((response) => {
      this.resetSMS = response.body;
      console.log(this.resetSMS.result);
        if (this.resetSMS.result == 0){
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
