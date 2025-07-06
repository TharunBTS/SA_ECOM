import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { AnalyticsData } from '../analytics-data.model';

@Component({
  selector: 'app-order-by-status',
  standalone : true,
  imports: [MatCardModule, CommonModule],
  templateUrl: './order-by-status.component.html',
  styleUrl: './order-by-status.component.css'
})
export class OrderByStatusComponent {


  @Input() statusData : AnalyticsData | null = null;

}
