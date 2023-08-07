import { Injectable, Injector } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthServiceService } from './auth-service.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthServiceService) {}

  TOKEN_KEY: string = "JWT_TOKEN";

  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    // const token = localStorage.getItem(this.TOKEN_KEY);
    // console.log('token');
    // let jwtToken = req.clone({
    //   setHeaders: {
    //     Authorization: 'Bearer '+ token
    //   }
      
    // });
    // console.log(Headers);
    // console.log(jwtToken);
    
    // return next.handle(jwtToken);


    
    const token = localStorage.getItem(this.TOKEN_KEY);

    // Check if the request URL matches the target URL for the token
    if (req.url.includes('http://localhost:9090/book/tickit') || req.url.includes('http://localhost:9090/pg/createOrder') || req.url.includes('http://localhost:9090/admin/removetrain') || req.url.includes('http://localhost:9090/admin/add')) {
      // Add the JWT token header only for the specified URL
      const jwtTokenReq = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token,
        },
      });

      return next.handle(jwtTokenReq);
    }

    // For all other requests, proceed without adding the JWT token header
    return next.handle(req);
  }
}
