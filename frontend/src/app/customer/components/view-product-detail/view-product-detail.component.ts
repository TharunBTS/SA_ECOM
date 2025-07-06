import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { UserStorageService } from '../../../services/storage/user-storage.service';

@Component({
  selector: 'app-view-product-detail',
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
  templateUrl: './view-product-detail.component.html',
  styleUrl: './view-product-detail.component.css'
})
export class ViewProductDetailComponent implements OnInit{

  productId: number;
  userId: string; 
  product : any;
  FAQS : any[] = [];
  reviews : any[] = [];


  constructor(
    private snackBar : MatSnackBar,
    private customerService : CustomerService,
    private activatedRoute : ActivatedRoute
  ){}

  ngOnInit()
  {
    this.productId = this.activatedRoute.snapshot.params['productId'];
     this.userId = UserStorageService.getUserId();
     console.log('Current user:', this.userId);
    this.getProductDetailById();
  }

//   getProductDetailById(){
//     this.customerService.getProductDetailById(this.productId).subscribe(res => {
//       this.product = res.productDto;
//       this.product.processedImg = 'data:image/jpeg;base64,' + res.productDto.byteImg;

  

//       this.FAQS = res.faqDtoList || [];
//       this.reviews = [];
//       console.log('✅ FAQs assigned:', this.FAQS);



// this.reviews = (res.reviewDtoList || [])
//     .filter(r => r.productId === this.productId)
//     .map(element => {
//       const returned = element.returnedimg || element.returnedImg;
//       const isJpeg = returned && returned.startsWith('/9j');
//       const mimeType = isJpeg ? 'jpeg' : 'png';
//       return {
//         ...element,
//         processedImg: returned ? `data:image/${mimeType};base64,${returned}` : null
//       };
//     });


//     })
//   }






getProductDetailById() {
  this.customerService.getProductDetailById(this.productId).subscribe(res => {
    this.product = res.productDto;
    this.product.processedImg = 'data:image/jpeg;base64,' + res.productDto.byteImg;

    this.FAQS = res.faqDtoList || [];
    console.log('✅ FAQs assigned:', this.FAQS);

    this.reviews = (res.reviewDtoList || [])
      // 🚨 critical line
      .map(element => {
        const returned = element.returnedimg || element.returnedImg;
        const isJpeg = returned && returned.startsWith('/9j');
        const mimeType = isJpeg ? 'jpeg' : 'png';
        return {
          ...element,
          processedImg: returned ? `data:image/${mimeType};base64,${returned}` : null
        };
      });

    console.log('✅ Reviews after filtering:', this.reviews);
  });
}



  addToWishlist() {
    const wishlistDto = {
      productId : this.productId,
      userId : UserStorageService.getUserId()
    }

    this.customerService.addProductToWishlist(wishlistDto).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Product Added to Wishlist Successfully",'Close',{duration : 5000});
      }else{
        this.snackBar.open('Already in Wishlist','ERROR',{duration : 5000});
      }
    })
  }


}







