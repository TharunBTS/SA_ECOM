import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterOutlet } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { CategoryResponse } from './category-response';

@Component({
  selector: 'app-post-category',
  standalone : true,
  imports: [
  RouterOutlet,
  CommonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  ReactiveFormsModule
  ],
  templateUrl: './post-category.component.html',
  styleUrl: './post-category.component.css'
})
export class PostCategoryComponent implements OnInit{

  categoryForm! : FormGroup;

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snackBar : MatSnackBar,
    private adminService : AdminService
  ){}

  ngOnInit() : void {
    this.categoryForm = this.fb.group({
      name : [null, [Validators.required]],
      description : [null, [Validators.required]],
    })
  }

  addCategory() : void {
    console.log("Form submission triggered");
    if(this.categoryForm.valid){
      this.adminService.addCategory(this.categoryForm.value).subscribe((res : CategoryResponse) => {
        if(res.id != null){
          this.snackBar.open('Category Posted Successfully!','Close',{
            duration : 5000
          });
          this.router.navigateByUrl('/admin/dashboard');
        }else{
          this.snackBar.open(res.message,'Close',{
            duration : 5000,
            panelClass : 'error snackbar'
          });
        }
      })
    }else{
        this.categoryForm.markAllAsTouched();
      }
    }
}
