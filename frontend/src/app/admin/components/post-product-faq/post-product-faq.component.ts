import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-post-product-faq',
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
  templateUrl: './post-product-faq.component.html',
  styleUrl: './post-product-faq.component.css'
})
export class PostProductFaqComponent {


  productId!: number;
  FAQForm! : FormGroup;


  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snackBar : MatSnackBar,
    private adminService : AdminService,
    private activatedRouter : ActivatedRoute
  ){}

  ngOnInit(){
      this.productId = +this.activatedRouter.snapshot.params["productId"];

    this.FAQForm = this.fb.group({
      question : [null, [Validators.required]],
      answer : [null, [Validators.required]]
    })
  }

  postFAQ()
  {
    this.adminService.postFAQ(this.productId, this.FAQForm.value).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("FAQ Posted Successfully","Close",{duration : 5000});
        this.router.navigateByUrl("/admin/dashboard");
      }else{
        this.snackBar.open("Something went wrong","Close",{duration : 5000, panelClass : 'error-snackbar'});
      }
    })
  }

}
