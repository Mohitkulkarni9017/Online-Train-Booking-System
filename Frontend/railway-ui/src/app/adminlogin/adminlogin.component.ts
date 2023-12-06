import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

interface UserCredential{
  name:string
  email:string
  password:string
  roles:string
}

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent {

  constructor(private http: HttpClient) {}

  successmsg: string ="";
  errorflag: boolean = true;


  

  name1: string = '';
  email1: string = '';
  password1: any = '';
  password2: any = '';
  roles: any = 'ROLE_ADMIN';

  user: UserCredential = {
    name : this.name1,
    email : this.email1,
    password : this.password1,
    roles: this.roles
  }

  formIsValid(): boolean {
    // Add any additional validation logic here
    // Return true if all the required fields are valid, else return false
    return (
      this.name1 &&
      this.email1 &&
      this.isEmailValid(this.email1) && // Check if the email is valid
      this.password1 &&
      this.password2 &&
      this.password1 === this.password2
    );
  }

  isEmailValid(email: string): boolean {
    // A simple email pattern that checks for @ symbol and .com/.org/.net/etc.
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(email);
  }

  onSubmit() :void {
    if( this.formIsValid())
    {
      const url = 'http://localhost:9194/auth/new';

      this.user.name = this.name1;
      this.user.email = this.email1;
      this.user.password = this.password1;
      
      this.http.post<string>(url,this.user).subscribe(
        (response:string) => {
          this.successmsg= response;
          console.log(this.user);
          console.log("success");
        },
        (error) => {
          console.log("error");
          console.log(this.user);
          console.log(error)
          this.successmsg=error.error.text
          if(this.successmsg!="This UserName is Already Registered.")
          {
            
            this.errorflag = false;
          }
        }
      )
    }

    else
    {
      this.successmsg = "Please fill in all fields correctly.";
      this.errorflag = true;

    }
  }
}
