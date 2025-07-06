import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone : true,
  imports: [
  CommonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  ReactiveFormsModule
],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm! : FormGroup;
  hidePassword = true;

  constructor(private fb : FormBuilder,
    private snackBar : MatSnackBar,
    private authService : AuthService,
    private router : Router
  )
  {
  }


  ngOnInit() : void {
    this.signupForm = this.fb.group({
      name : [null, [Validators.required]],
      email : [null, [Validators.required, Validators.email]],
      password : [null, [Validators.required]],
      confirmPassword : [null, [Validators.required]],
    })
  }



  togglePasswordVisibility()
  {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() : void {
    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if(password !== confirmPassword)
    {
      this.snackBar.open('Passwords do not match','Close',{duration:5000,panelClass : 'error-snackbar'});
      return;
    }

    this.authService.register(this.signupForm.value).subscribe(
    (response : any) => {
      this.snackBar.open('Sign up successful','Close',{duration : 5000});
      this.router.navigateByUrl("/login")
    },
    (error : any) => {
      this.snackBar.open('Sign up failed, Please try again.','Close',{duration:5000,panelClass : 'error-snackbar'});
    }
  )

  }


  

}
