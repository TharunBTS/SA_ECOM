import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  standalone : true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
     MatIconModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  products : any [] = [];
  searchProductForm! : FormGroup;

  constructor(
    private adminService : AdminService,
    private fb : FormBuilder,
    private snackBar : MatSnackBar
  ){

  }


  ngOnInit(){
     console.log('✅ ADMIN DASHBOARD INIT');
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title : [null, [ Validators.required]]
    })
  }


  getAllProducts(){
    this.products = [];
    this.adminService.getAllProducts().subscribe(res => {
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
    this.adminService.getAllProductsByName(title).subscribe(res => {
      console.log(res);
     res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
     });
    })
  }


  deleteProduct(productId : any) {
    this.adminService.deleteProduct(productId).subscribe(res => {
      if(res == null){
        this.snackBar.open('Product deleted Successfully','Close',{
          duration : 5000
        });
        this.getAllProducts();
      }else{
        this.snackBar.open(res.message,'Close',{
          duration : 5000,
          panelClass : 'error-snackbar'
        });
      }
    })
  }


}
