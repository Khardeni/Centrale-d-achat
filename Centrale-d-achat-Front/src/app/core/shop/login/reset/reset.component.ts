import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/AuthService';
import { UserService, data } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {
  data = this.userService.data;
  Emailverif : any;
  timerInterval : any;
 

  constructor(private userService: UserService, private authService : AuthService, private router:Router){
    if (this.authService.isAuthenticated())
    this.router.navigate(['/unauthorized']);
  }
    resetOption: any;

  ngOnInit(): void {
    this.resetOption="email";
    console.log(this.data);
  }

  onOptionSelected(event) {
    this.resetOption = event.target.value;
  }

  reset() {
    Swal.fire({
      title: 'Verifying your account !',
      html: 'I will close in <b></b> milliseconds.',
      timer: 12000,
      timerProgressBar: true,
      didOpen: () => {
        Swal.showLoading()
        const b = Swal.getHtmlContainer().querySelector('b')  
        this.timerInterval = setInterval(() => {
          b.textContent = Swal.getTimerLeft().toString();
        }, 100)
      },
      willClose: () => {
        clearInterval(this.timerInterval)
      }
    }).then((result) => {
      /* Read more about handling dismissals below */
      if (result.dismiss === Swal.DismissReason.timer) {
        console.log('I was closed by the timer')
      }
    });
    this.userService.checkEmail(this.resetOption,this.data).subscribe((response) => {
      this.Emailverif = response.body;
      console.log(this.Emailverif.result);
        if (this.Emailverif.result == 0)
        {            setTimeout(() => {
          Swal.fire({
            title: 'this Email is not registred',
            text: 'Try logging in or registering again!',
            icon: 'error',
            showCancelButton: false,
            confirmButtonText: 'OK'
          });
          
        }, 4000);
           
        }
        else if (this.Emailverif.result == 1){
          Swal.fire({
            title: 'email found',
            text: 'Please check your email',
            icon: 'success',
            showCancelButton: false,
            confirmButtonText: 'OK'
          });
          this.authService.setEmail(this.data.email.email);
          this.router.navigate(['/resetMail']);
  
        }
        else if (this.Emailverif.result == 3)
        {
          setTimeout(() => {
            Swal.fire({
              title: 'this phone number is not registred',
              text: 'Try logging in or registering again!',
              icon: 'error',
              showCancelButton: false,
              confirmButtonText: 'OK'
            });
          }, 4000);
 
        }
        else if (this.Emailverif.result == 2) {
          Swal.fire({
            title: 'Phone number found',
            text: 'please check your phone',
            icon: 'success',
            showCancelButton: false,
            confirmButtonText: 'OK'
          });
          this.authService.setPhone(this.data.phone.phone);
          this.router.navigate(['/resetSMS']);
        }
      });

      }
  }
