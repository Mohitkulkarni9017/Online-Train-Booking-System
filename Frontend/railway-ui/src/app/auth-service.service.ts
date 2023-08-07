import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


interface AuthRequest{
  username:string
  password:string
}

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  loginflag:boolean = false;

  TOKEN_KEY: string = "JWT_TOKEN";

  constructor(private http: HttpClient) {}

  login(body:AuthRequest) {
    const API_URL = 'http://localhost:9194/auth/authenticate';

    return this.http.post<any>(API_URL, body).subscribe(
      (Response:string) => {
        console.log("success");
          if (Response) {
            localStorage.setItem(this.TOKEN_KEY, Response);
          }
      },
      (Error) => {
        console.log(Error);
        
        
        return 0;
      });
  }

  getToken(): any {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  changeflag()
  {
    this.loginflag = true;
  }

  getflag()
  {
    return this.loginflag;
  }
}
