import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-view-ordered-components',
  standalone : true,
  imports: [
    RouterModule,
    CommonModule,
     MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule
  ],
  templateUrl: './view-ordered-components.component.html',
  styleUrl: './view-ordered-components.component.css'
})
export class ViewOrderedComponentsComponent {
    
  orderId: any;
  OrderedProductDetailsList = [];
  totalAmount : any;
  constructor(
      private activatedRoute : ActivatedRoute,
      private customerService : CustomerService
    ){}


    ngOnInit(){
        this.orderId = this.activatedRoute.snapshot.params['orderId'];

      this.getOrderedProductDetailsByOrderId();
    }

    getOrderedProductDetailsByOrderId(){
      this.customerService.getOrderedProducts(this.orderId).subscribe(res => {
        res.productDtoList.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
          this.OrderedProductDetailsList.push(element);
        });
        this.totalAmount = res.orderAmount;
      })
    }

}
