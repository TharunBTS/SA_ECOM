// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';
// import { UserStorageService } from '../../services/storage/user-storage.service';

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserStorageService } from "../../services/storage/user-storage.service";


// const API_URL = 'http://localhost:8080/api/customer/';


// @Injectable({
//   providedIn: 'root'
// })
// export class CustomerService {

//   constructor(private http : HttpClient) { }


//    getAllProducts(): Observable<any> {
//       const token = UserStorageService.getToken();
  
//       const headers = new HttpHeaders({
        
//         'Authorization': `Bearer ${token}`
//       });
  
//       return this.http.get<any>(API_URL + 'products', { headers });
//     }
  
  
//     getAllProductsByName(name : any): Observable<any> {
//       const token = UserStorageService.getToken();
  
//       const headers = new HttpHeaders({
        
//         'Authorization': `Bearer ${token}`
//       });
  
//       return this.http.get<any>(API_URL + `search/${name}`, { headers });
//     }


// }


const API_URL = 'http://localhost:8080/api/customer/';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http : HttpClient) {}

  getAllProducts(): Observable<any> {
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + 'products', { headers });
  }

  getAllProductsByName(name : any): Observable<any> {
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `search/${name}`, { headers });
  }


  addToCart(productId : any): Observable<any> {
    const cartDto = {
      productId : productId,
      userId : UserStorageService.getUserId()
    }
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + 'cart', cartDto, { headers });
  }



  getCartByUserId(): Observable<any> {
    const userId = UserStorageService.getUserId()
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `cart/${userId}`, { headers });
  }

  increasedProductQuantity(productId : any): Observable<any> {
    const cartDto = {
      productId : productId,
      userId : UserStorageService.getUserId()
    }
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + 'addition', cartDto, { headers });
  }


  decreasedProductQuantity(productId : any): Observable<any> {
    const cartDto = {
      productId : productId,
      userId : UserStorageService.getUserId()
    }
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + 'deduction', cartDto, { headers });
  }


  placeOrder(orderDto : any): Observable<any> {
    orderDto.userId = UserStorageService.getUserId();
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + 'placeOrder', orderDto, { headers });
  }

  getOrdersByUserId(): Observable<any> {
    const userId = UserStorageService.getUserId();
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `myOrders/${userId}`, { headers });
  }



  getOrderedProducts(orderId : number): Observable<any> {
    
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `ordered-products/${orderId}`, { headers });
  }


  giveReview(reviewDto : any): Observable<any> {
    
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + `review`, reviewDto, { headers });
  }

  getProductDetailById(productId : number): Observable<any> {
    
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `product/${productId}`, { headers });
  }
 
 
 
  addProductToWishlist(wishlistDto : any) : Observable<any> {
    
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(API_URL + 'wishlist', wishlistDto, { headers });
  }




  getWishlistByUserId() : Observable<any> {
    const userId = UserStorageService.getUserId();
    const token = UserStorageService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(API_URL + `wishlist/${userId}`, { headers });
  }



}
