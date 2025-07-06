import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  standalone : true,
  imports: [
  RouterModule,
  CommonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm! : FormGroup;
  hidePassword = true;


  constructor(private fb : FormBuilder,
    private snackBar : MatSnackBar,
    private authService : AuthService,
    private router : Router
  )
  {
  }



  ngOnInit() : void {
    this.loginForm = this.fb.group({
      email : [null, [Validators.required, Validators.email]],
      password : [null, [Validators.required]],
      
    })
  }



  togglePasswordVisibility()
  {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() : void {
    const username = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;

    
    this.authService.login(username, password).subscribe(
    (response : any) => {
      this.snackBar.open('Login successful','OK',{duration : 5000});
      if(UserStorageService.isAdminLoggedIn()){
        this.router.navigateByUrl("admin/dashboard");
      }else if(UserStorageService.isCustomerLoggedIn()){
        this.router.navigateByUrl("customer/dashboard");
      }
      
    },
    (error : any) => {
      this.snackBar.open('Bad Credentials','Error',{duration:5000});
    }
  )

  }



}
