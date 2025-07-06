import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { OrderByStatusComponent } from "./order-by-status/order-by-status.component";
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { AnalyticsData } from './analytics-data.model';

@Component({
  selector: 'app-analytics',
  standalone : true,
  imports: [OrderByStatusComponent, CommonModule,MatCardModule],
  templateUrl: './analytics.component.html',
  styleUrl: './analytics.component.css'
})
export class AnalyticsComponent {


  statusData :  AnalyticsData | null = null;;


  constructor(
    private adminService : AdminService
  ){}


  ngOnInit(){
    this.adminService.getAnalytics().subscribe(res => {
      console.log(res);
      this.statusData = res;
    })
  }



}
