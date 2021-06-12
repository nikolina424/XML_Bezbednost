import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  isCollapsed = false;
  userID: number = 0;

  constructor(private router: Router, private authService : AuthService) { }

  ngOnInit(): void {
    this.authService.checkUser();
  }

  Feed(){
    this.router.navigate(['homepage/feed']);
  }

  Profile() {
    this.router.navigate(['homepage/profile']);
  }
  
  Search(){
    this.router.navigate(['homepage/search']);
  }

  logout(){
    //ovde obrisati sve sto se tice tokena
    localStorage.clear();
    this.router.navigate(['login']);
  }

  NewPost(){
    this.router.navigate(['homepage/new-post']);
  }

  NewStory(){
    this.router.navigate(['homepage/new-story']);
  }

  NewVerificationRequest(){
    this.router.navigate(['homepage/new-verification-request']);
  }


}
