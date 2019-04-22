import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
export const USER_NAME = "username";
export const TOKEN_NAME = "jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private SpringRegisterEndPoint: string;
  private SpringSaveEndPoint: string;
  private SpringLoginEndPoint: string;

  constructor(private httpclient: HttpClient) { 
//    this.SpringRegisterEndPoint="http://localhost:8085/api/v1/usertrackservice/";
//    this.SpringSaveEndPoint="http://localhost:8084/api/v1/userservice/";

//    this.SpringRegisterEndPoint="http://localhost:8086/orchestrationservice/api/v1/user";

      this.SpringRegisterEndPoint="http://localhost:8086/usertrackservice/api/v1/usertrackservice/register"
      this.SpringLoginEndPoint="http://localhost:8086/authenticationservice/api/v1/userservice/login";

  }

  registerUser(newUser) {
    const url = this.SpringRegisterEndPoint;
    return this.httpclient.post(url, newUser, {observe: "response"});
  }

  // saveUser(newUser) {
  //   const url = this.SpringSaveEndPoint + "save";
  //   return this.httpclient.post(url, newUser);
  // }

  loginUser(newUser) {
    const url = this.SpringLoginEndPoint;
    sessionStorage.setItem(USER_NAME, newUser.username);
    return this.httpclient.post(url, newUser, {observe: "response"});
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  isTokenActive(token?: string): boolean {
    if(localStorage.getItem(TOKEN_NAME)) {
      console.log("Inside isTokenActive! Returning true")
      return true;
    } else {
      console.log("Inside isTokenActive! Returning false")
      return false;
    }
  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem(TOKEN_NAME);
    sessionStorage.clear();
    console.log("Logged out!")
  }

}
