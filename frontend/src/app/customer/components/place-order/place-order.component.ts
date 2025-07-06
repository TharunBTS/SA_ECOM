import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-place-order',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule
  ],
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent {

  orderForm! : FormGroup;

  constructor(
    private customerService : CustomerService,
    private fb : FormBuilder,
    private snackBar : MatSnackBar,
    private router : Router,
    public dialog : MatDialog
  ){}

  ngOnInit(){
    this.orderForm = this.fb.group({
      address : [null, [Validators.required]],
      orderDescription : [null, [Validators.required]]
    })
  }

  placeOrder(){

    this.customerService.placeOrder(this.orderForm.value).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Order Placed Successfully",'Close',{duration : 5000})
        this.router.navigateByUrl("/customer/my-orders")
       this.closeForm();
      }else{
        this.snackBar.open("Something went wrong",'Close',{duration : 5000})
      }
    })
    
  }

  closeForm(){
    this.dialog.closeAll();
  }

}
