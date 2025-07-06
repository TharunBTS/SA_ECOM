// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';
// import { UserStorageService } from '../../services/storage/user-storage.service';

// const BASIC_URL = 'http://localhost:8080/'


// @Injectable({
//   providedIn: 'root'
// })
// export class AdminService {

//   constructor(private http : HttpClient) { }

//   addCategory(categoryDto : any) : Observable<any>{
//     return this.http.post(BASIC_URL + 'api/admin/category',categoryDto,{
//       headers : this.createAuthorizationHeader(),
//     });
//   }

//   private createAuthorizationHeader() : HttpHeaders{
//   return new HttpHeaders().set(
//     'Authorization','Bearer '+UserStorageService.getToken()
//   )
// }
// }


import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../services/storage/user-storage.service'; // adjust path
import { CategoryResponse } from '../components/post-category/category-response'; // adjust path to your interface

const API_URL = 'http://localhost:8080/api/admin/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) {}

  addCategory(data: any): Observable<CategoryResponse> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<CategoryResponse>(API_URL + 'category',data, { headers });
  }



  addProduct(productDto: any): Observable<any> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<any>(API_URL + 'product',productDto, { headers });
  }


  getAllCategory(): Observable<any> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(API_URL + 'categories', { headers });
  }



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



  deleteProduct(productId: any): Observable<any> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<any>(API_URL + `product/${productId}`, { headers });
  }



  getPlacedOrders() : Observable<any>{
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(API_URL + 'placedOrders', { headers });
  }



  changeOrderStatus(orderId : number, status : string) : Observable<any>{
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(API_URL + `order/${orderId}/${status}`, { headers });
  }


  postFAQ(productId : number, faqDto : any) : Observable<any>{
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<any>(API_URL + `faq/${productId}`, faqDto ,{ headers });
  }


  getProductById(productId ): Observable<any> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(API_URL + `product/${productId}`, { headers });
  }


  updateProduct(productId : any, productDto: any): Observable<any> {
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<any>(API_URL + `product/${productId}`,productDto, { headers });
  }



  getAnalytics() : Observable<any>{
    const token = UserStorageService.getToken();

    const headers = new HttpHeaders({
      
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(API_URL + 'order/analytics', { headers });
  }



}
