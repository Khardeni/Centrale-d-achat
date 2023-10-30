import { Component, OnInit } from '@angular/core';
import { error } from 'jquery';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private userService: UserService, private router:Router) {
  }
  listUsers: any = [];
  user = {
    userName: "",
    userPassword: "",
    userFirstName: "",
    userLastName: "",
    userCin: "",
    userEmail: "",
    userAdress: "",
    userPhone : ""
  }
  ngOnInit(): void {
    this.userService.showuser().subscribe((data => this.listUsers = data))
  }

  addRoleToUser(roleName: string, userName: string) {
    this.userService.addRoleToUser(roleName, userName).subscribe((data) => {
      console.log(data);
    })
  }

  deleteUser(useraName : string){
    this.userService.deleteUser(useraName).subscribe((response) => {
      console.log(response.status);
      if (response.status === 200) {
        this.router.navigate(['/back/admin']); 
        Swal.fire({
          title: 'User deleted !',
          confirmButtonText: 'Ok',
          icon: 'warning'
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            Swal.fire( {  
              title: 'deleted !',
              showConfirmButton: false,
            icon: 'success' })
            setTimeout(() => {
              location.reload();
            }, 1000);          } 
        })
      }
      },
      (error) => {
        if(error.status === 400) {     
        Swal.fire({
        title: 'Error',
        text: 'oops !',
        icon: 'error',
        showCancelButton: false,
        confirmButtonText: 'Ok !'}).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            setTimeout(() => {
              location.reload();
            }, 1000);          } 
        });}
      else console.log(error.status);
      },
       null
    );
  }
}
