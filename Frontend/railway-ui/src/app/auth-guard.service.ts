import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private router: Router) {}

 

  canActivate(

    next: ActivatedRouteSnapshot,

    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

 

    // Implement your authentication logic here

    // For example, you can check if the user is logged in and has valid credentials

    const isAuthenticated = !!localStorage.getItem('JWT_TOKEN'); // Check if the token is present in LocalStorage

 

    console.log(isAuthenticated);

    if (isAuthenticated) {

      return true;

    } else {

      // Redirect to the login page

      this.router.navigate(['/login']);

      return false;

    }

  }
}
