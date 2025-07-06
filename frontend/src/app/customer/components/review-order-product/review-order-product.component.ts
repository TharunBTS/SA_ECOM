import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-review-order-product',
  standalone : true,
  imports: [
    CommonModule,
     ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule,
  ],
  templateUrl: './review-order-product.component.html',
  styleUrl: './review-order-product.component.css'
})
export class ReviewOrderProductComponent {

  constructor(
    private custtomerService : CustomerService,
        private snackBar : MatSnackBar,
        private fb : FormBuilder,
        public dialog : MatDialog,
        private router : Router,
        private activatedRouter : ActivatedRoute
  ){}

  productId : number;
  reviewForm! : FormGroup;
  selectedFile : File | null;
  imagePreview : string | ArrayBuffer | null;

  ngOnInit()
  {
    this.productId = this.activatedRouter.snapshot.params['productId'];
    this.reviewForm = this.fb.group({
      rating : [null, [Validators.required]],
      description : [null, [Validators.required]]
    })
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      // Show image preview
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  submitForm(){

    const formData = new FormData();
    formData.append('img',this.selectedFile);
    formData.append('productId',this.productId.toString());
    formData.append('userId',UserStorageService.getUserId().toString());
    formData.append('rating',this.reviewForm.get('rating').value);
    formData.append('description',this.reviewForm.get('description').value);

    this.custtomerService.giveReview(formData).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Review Posted Successfully","Close",{duration : 5000});
        this.router.navigateByUrl("/customer/orders");
      }else{
        this.snackBar.open("Something went wrong","ERROR",{duration : 5000});
      }
    })
  }

}
