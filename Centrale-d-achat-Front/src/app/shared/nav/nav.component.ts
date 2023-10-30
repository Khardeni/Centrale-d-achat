import { Component, HostListener } from '@angular/core';
import { faGear } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router} from '@angular/router';

import { faList } from '@fortawesome/free-solid-svg-icons';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css','../../assets/css/app.css']
})
export class NavComponent {
  usermenu=0;
    toggleIcon = faList;
    settingsIcon = faGear;
    searchIcon = faSearch;
    text="";
    fullname: any;
    constructor(private route: ActivatedRoute, private router: Router, private userService:UserService) { }
    ngOnInit(){
      this.fullname=localStorage.getItem("fullname");
    }
    supportClient(){
      //console.log("entered");
      this.text = (<HTMLInputElement>document.getElementById("search_txt")).value;
      let url = '';
      if(!(this.router.url.includes("support"))){
        url = this.router.url;
        this.router.navigate(['support/'+this.text]);
      }else{
        this.router.navigateByUrl(url, { skipLocationChange: true }).then(() => {
          this.router.navigate(['support/'+this.text]);
      });
      }
    }

    collapse(){
      console.log(document.getElementById("sidebar").classList.contains('collapse'));
      if(document.getElementById("sidebar").classList.contains('collapse')){
        document.getElementById("sidebar").classList.remove('collapse');
        document.getElementById("body_admin").style.paddingLeft='19%';
        document.getElementById("sidebar_toggle").style.marginLeft='20%';
      }else{
        document.getElementById("sidebar").classList.add('collapse');
        document.getElementById("body_admin").style.paddingLeft='3%';
        document.getElementById("sidebar_toggle").style.marginLeft='2%';
      }
    }

    displayUser(){
      if(this.usermenu==0){
        document.getElementById("userinfo").classList.add("show");  
        this.usermenu=1;
      }else if(this.usermenu==1){
        document.getElementById("userinfo").classList.remove("show");
        this.usermenu=0;
      }
    }

    @HostListener('window:scroll', ['$event']) // for window scroll events
      onScroll(event) {
        document.getElementById("userinfo").classList.remove("show");
      }

    logout(){
      this.userService.logout();
      this.router.navigate(['/login'],{relativeTo:this.route});
    }
}
