import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders',
  imports: [
    CommonModule,
     MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule
  ],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent {

  orders : any;

  constructor(private adminService : AdminService,
    private snackBar : MatSnackBar
  ){}

  ngOnInit(){
    this.getPlacedOrders();
  }

  getPlacedOrders(){
    this.adminService.getPlacedOrders().subscribe(res => {
      this.orders = res;
    })
  }


  changeOrderStatus(orderId : number, status : string){
    this.adminService.changeOrderStatus(orderId,status).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Order Status Changed Successfully","Close",{duration : 5000});
        this.getPlacedOrders();
      }else{
        this.snackBar.open("Something went wrong","Close",{duration : 5000});
      }
    })
  }

}
