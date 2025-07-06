import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cdashboard',
  imports: [
    RouterModule,
    CommonModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
     MatIconModule
  ],
  templateUrl: './cdashboard.component.html',
  styleUrl: './cdashboard.component.css'
})
export class CdashboardComponent implements OnInit {
  products : any [] = [];
  searchProductForm! : FormGroup;

  constructor(
    private customerService : CustomerService,
    private fb : FormBuilder,
    private snackBar : MatSnackBar
  ){

  }


  ngOnInit(){
     console.log('✅ Customer DASHBOARD INIT');
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title : [null, [ Validators.required]]
    })
  }


  getAllProducts(){
    this.products = [];
    this.customerService.getAllProducts().subscribe(res => {
      console.log(res);
     res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
     });
    })
  }



  submitForm(){
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.customerService.getAllProductsByName(title).subscribe(res => {
      console.log(res);
     res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
     });
    })
  }


  addToCart(id : any){
    this.customerService.addToCart(id).subscribe(res => {
      this.snackBar.open("Product added to cart successfully","Close",{ duration : 5000})
    })
  }



}
