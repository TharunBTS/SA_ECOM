import { Component } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { MatCard, MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-trackorder',
  imports: [
    CommonModule,
    MatCardModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './trackorder.component.html',
  styleUrl: './trackorder.component.css'
})
export class TrackorderComponent {

  searchOrderForm! : FormGroup;
  order : any;
  constructor(
    private fb : FormBuilder,
    private authService : AuthService
  ){}

  ngOnInit(){
    this.searchOrderForm = this.fb.group({
      trackingId : [null,[Validators.required]]
    })
  }

  submitForm(){
    this.authService.getOrderByTrackingId(this.searchOrderForm.get('trackingId').value).subscribe(res => {
      // console.log(res);
      this.order = res;
      console.log(this.order);
    })
  }

}
