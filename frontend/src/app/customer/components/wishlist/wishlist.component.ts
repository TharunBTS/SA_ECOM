import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-wishlist',
  imports: [CommonModule],
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent {
  products : any[] = [];

  constructor(
    private customerService : CustomerService
  ){}

  ngOnInit(){
    this.getWishlistByUserId();
  }

  getWishlistByUserId(){
    this.customerService.getWishlistByUserId().subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,'+element.returnedImg;
        this.products.push(element);
      })
    })
  }
}
