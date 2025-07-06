import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PlaceOrderComponent } from '../place-order/place-order.component';

@Component({
  selector: 'app-cart',
  standalone : true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [
    CurrencyPipe
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  cartItems : any[] = [];
  order : any;

  constructor(private custtomerService : CustomerService,
    private snackBar : MatSnackBar,
    private fb : FormBuilder,
    public dialog : MatDialog
  ){}

  ngOnInit() : void {
    this.getCart();
  }


  getCart(){
    this.cartItems = [];
    this.custtomerService.getCartByUserId().subscribe(res => {
      this.order = res;
      res.cartItems.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + (element.returnedImg);
        this.cartItems.push(element);
        console.log(element);
      })
    })
  }


  increasedQuantity(productId : any ){
    this.custtomerService.increasedProductQuantity(productId).subscribe(res => {
      this.snackBar.open("Product quantity increased.",'Close',{duration : 5000});
      this.getCart();
    })
  }


  decreasedQuantity(productId : any ){
    this.custtomerService.decreasedProductQuantity(productId).subscribe(res => {
      this.snackBar.open("Product quantity decreased.",'Close',{duration : 5000});
      this.getCart();
    })
  }

  placeOrder(){
    this.dialog.open(PlaceOrderComponent);
  }





}
