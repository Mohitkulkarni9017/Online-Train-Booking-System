import { AfterViewInit,ChangeDetectorRef,Component,ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from 'src/app/auth-service.service';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { SharedService } from '../shared.service';

interface AuthRequest{
  username:string
  password:string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  // @ViewChild(HeaderComponent) headerComponent!: HeaderComponent;

  TOKEN_KEY: string = "JWT_TOKEN";

  constructor(private http: HttpClient, private router : Router,private sharedService: SharedService) {}


  ngOnInit() {
    // You can trigger the changelogin() method in the HeaderComponent
    
  }
   
  
  errorflag: boolean = false;

  username: string = '';
  password: string = '';


  res:any='';
  // ngAfterViewInit() {
  //   if (this.headerComponent) {
  //     console.log("Executed");
  //     this.headerComponent.changelogin();
  //   }
  // }

  body:AuthRequest = {
    username : this.username,
    password : this.password
  }

  submit(){

    const url = 'http://localhost:9194/auth/authenticate';

    this.body.username = this.username;
    this.body.password = this.password;

    this.http.post<string>(url,this.body).subscribe(
      (response:string) => {
        console.log(this.body);
        console.log("success");
        console.log(response);
        if (response) {
          localStorage.setItem(this.TOKEN_KEY, response);
        }
        console.log(localStorage.getItem(this.TOKEN_KEY));
        let url1 = 'http://localhost:9194/auth/getroles/'+this.username;
        console.log(url);
        this.http.get<string>(url1).subscribe(
          (response:string)=> {
            console.log("got the role");
            console.log(response);
            
          },
          (error) => {
            console.log("didnt got the role");
            console.log(error);
          }
        )
 
      },
      (error) => {
        console.log(this.body);
        // console.log(error.error.text)
        if (error.error.text) {
          localStorage.setItem(this.TOKEN_KEY, error.error.text);
        }
        console.log(localStorage.getItem(this.TOKEN_KEY));
        console.log(localStorage.getItem(this.TOKEN_KEY));
        let url1 = 'http://localhost:9194/auth/getroles/'+this.username;
        console.log(url);
        this.http.get<string>(url1,{ responseType: 'text' as 'json' }).subscribe(
          (response:string)=> {
            console.log("got the role");
            console.log(response);
            console.log("Check");
            console.log("123456789");
            this.sharedService.triggerChangeLogin();
            // if (this.headerComponent) {
            //   console.log("Executed");
              
            //   this.headerComponent.changelogin();
            // }
            // this.obj.changelogin()
            // this.headerComponent.changelogin();
            if(response==="ROLE_USER")
            {
              console.log("navigate");
              this.router.navigate([''])
            }
            else if(response==="ROLE_ADMIN")
            {
              this.router.navigate(['/admin'])
            }

          },
          (error) => {
            console.log("didnt got the role");
            console.log(error);
          }
        )
      }
    )

  
  
  }
}
