import { Injectable } from '@angular/core';

const TOKEN = 'ecom-token';
const USER = 'ecom-user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token : string) : void {
   window.localStorage.removeItem(TOKEN);
   window.localStorage.setItem(TOKEN,token);
  }

  public saveUser(user) : void {
   window.localStorage.removeItem(USER);
   window.localStorage.setItem(USER,JSON.stringify(user));
  }


  static getToken() : string {
    return localStorage.getItem(TOKEN);
  }

  static getUser() : any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId() : string {
    const User = this.getUser();
    if(User == null){
      return '';
    }
    return User.userId;
  }
  static getUserRole() : string {
    const User = this.getUser();
    if(User == null){
      return '';
    }
    return User.role;
  }

  static isAdminLoggedIn() : boolean {
    if(this.getToken() === null) {
      return false;
    }
    const role : string = this.getUserRole();
    return role == 'ADMIN'
  }
  static isCustomerLoggedIn() : boolean {
    if(this.getToken() === null) {
      return false;
    }
    const role : string = this.getUserRole();
    return role == 'CUSTOMER'
  }

  static signOut() : void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
