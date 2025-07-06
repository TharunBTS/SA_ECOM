import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router, RouterOutlet } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-product',
  standalone : true,
  imports: [
    CommonModule,
    RouterOutlet,
  ReactiveFormsModule,
  FormsModule,
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatSelectModule,
  MatOptionModule,
  MatSnackBarModule
  ],
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.css'
})
export class PostProductComponent implements OnInit{


  productForm : FormGroup;
  listOfCategories : any = [];
  SelectedFile : File | null;
  imagePreview : string | ArrayBuffer | null;

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snackBar : MatSnackBar,
    private adminService : AdminService
  ){}




  onFileSelected(event : any) {
    this.SelectedFile = event.target.files[0];
    this.previewImage();
  }


  previewImage(){
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.SelectedFile);
  }

  ngOnInit() : void {
    this.productForm = this.fb.group({
      categoryId : [null, [Validators.required]],
      name : [null, [Validators.required]],
      price : [null, [Validators.required]],
      description : [null, [Validators.required]],
    });

    this.getAllCategories();
  }

  getAllCategories() {
    this.adminService.getAllCategory().subscribe((res) =>  {
      this.listOfCategories = res;
    })
  }

  addProduct() : void {

    if(this.productForm.valid){
      const formData : FormData = new FormData;
      formData.append('img', this.SelectedFile);
      formData.append('categoryId', this.productForm.get('categoryId').value);
      formData.append('name' , this.productForm.get('name').value);
      formData.append('description', this.productForm.get('description').value);
      formData.append('price', this.productForm.get('price').value);

      this.adminService.addProduct(formData).subscribe((res) => {
        console.log(res);
        if(res.id != null){
          this.snackBar.open('Product Posted Successfully','Close', {
            duration : 5000
          });
          this.router.navigateByUrl('/admin/dashboard');
        }else{
          this.snackBar.open(res.message,'ERROR', {
            duration : 5000
          });
        }
      })
    }else{
      for(const i in this.productForm.controls){
        this.productForm.controls[i].markAsDirty();
        this.productForm.controls[i].updateValueAndValidity();
      }
    }

  }

}
