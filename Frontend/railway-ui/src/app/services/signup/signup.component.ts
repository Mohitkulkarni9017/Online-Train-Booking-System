import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

interface UserCredential{
  name:string
  email:string
  password:string
  roles:string
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  successmsg: string ="";
  errorflag: boolean = true;

  constructor(private http: HttpClient) { }

  name1: string = '';
  email1: string = '';
  password1: any = '';
  password2: any = '';
  roles: any = 'ROLE_USER';


  user: UserCredential = {
    name : this.name1,
    email : this.email1,
    password : this.password1,
    roles: this.roles
  }

  onSubmit() :void {
    if(this.password1===this.password2)
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
      this.successmsg = "Passwords are Mismatch Check Properly";
      this.errorflag = true;

    }
  }

}
