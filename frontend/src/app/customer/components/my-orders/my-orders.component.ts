import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  standalone : true,
  imports: [
    RouterOutlet,
    RouterModule,
    CommonModule,
     MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule
  ],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css'
})
export class MyOrdersComponent {
  myOrders : any;

  constructor(private customerService : CustomerService){}

  ngOnInit(){
    this.getMyOrders();
  }
  getMyOrders(){
    this.customerService.getOrdersByUserId().subscribe(res => {
      this.myOrders = res;
    })
  }
}
