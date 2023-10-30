import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { error } from 'jquery';
import { timeout } from 'rxjs';
import { AuthService } from 'src/app/shared/services/AuthService';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent {

  activateToken:any;
  err : any;
  timerInterval : any;
  
  constructor(private activatedRoute : ActivatedRoute, private userService: UserService, private authService : AuthService, private router:Router){
    if (this.authService.isAuthenticated())
    this.router.navigate(['/unauthorized']);
  }
ngOnInit(){
  this.activatedRoute.params.subscribe(s => { this.activateToken=s['activateToken'] });
  
  Swal.fire({
  title: 'Verifying your account !',
  html: 'I will close in <b></b> milliseconds.',
  timer: 4000,
  timerProgressBar: true,
  didOpen: () => {
    Swal.showLoading()
    const b = Swal.getHtmlContainer().querySelector('b')  
    this.timerInterval = setInterval(() => {
      b.textContent = Swal.getTimerLeft().toString()
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

  console.log(this.activateToken);

 if(this.activateToken !== null){
    this.userService.verifyAccount(this.activateToken).subscribe({
      error :(e) => {
        {  
           if (e.status === 400){
          
            setTimeout(() => {
              if (e.status === 400) {
                Swal.fire({
                  title: 'Your token is invalid or has expired',
                  text: 'Try logging in or registering again!',
                  icon: 'error',
                  showCancelButton: false,
                  confirmButtonText: 'OK'
                });
                console.log(e.status);
                this.router.navigate(['/login']);
              } else {
                Swal.fire('Success', 'Your account has been verified. Please login!', 'success');
                console.log('ok');
                this.router.navigate(['/login']);
              }
            }, 4000);
          }

          else  { 

            Swal.fire('Sucess','Your account has been verified, please login!','success');
          console.log("ok");
            this.router.navigate(['/login']); 
            }
          }
          
        }, 
        next: () =>  {
          Swal.fire('Sucess','Your account has been verified, please login!','success');
          console.log("ok");
          this.router.navigate(['/login']);
      }
      }
      );
    } 

    else {
    console.log("Wrong url")
    this.router.navigate(['/unauthorized']);
    }

}
}
