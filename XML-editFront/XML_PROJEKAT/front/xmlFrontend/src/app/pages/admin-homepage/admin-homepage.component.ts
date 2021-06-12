import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.css']
})
export class AdminHomepageComponent implements OnInit {

  isCollapsed = false;
  userID: number = 0;

  constructor(private router: Router, private authService : AuthService) { }

  ngOnInit(): void {
    this.authService.checkAdmin();
  }

  logout(){
    //ovde obrisati sve sto se tice tokena
    localStorage.clear();
    this.router.navigate(['login']);
  }

  ViewVerificationRequest(){
    this.router.navigate(['admin/viewVerifReq']);
  }


}
