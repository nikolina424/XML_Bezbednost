import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm!: FormGroup;
  token: any;
  decoded_token: any;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  constructor(private fb: FormBuilder, private authService : AuthService, private toastr : ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });

  }
  
  login(){
    if(this.validateForm.valid){
      const body = {
        username : this.validateForm.get('username')?.value,
        password : this.validateForm.get('password')?.value
      }

      this.authService.login(body).subscribe( data => {
        if(data == null)
        this.toastr.error("Bad login credentials");
        else
        this.toastr.success("Logged in");

        localStorage.setItem('token', JSON.stringify(data.token));
        console.log(this.getDecodedAccessToken(data.token));
        this.decoded_token = this.getDecodedAccessToken(data.token);
        if(this.decoded_token.type === 'Administrator'){
          this.router.navigate(['admin']);
        }else if(this.decoded_token.type === 'User'){
          this.router.navigate(['homepage']);
        }else{
          this.toastr.error("Invalid token");
        }
      })
    }    
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

  register(){
    this.router.navigate(['registration']);
  }
}
