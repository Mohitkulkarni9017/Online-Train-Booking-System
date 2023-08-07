import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-removetrain',
  templateUrl: './removetrain.component.html',
  styleUrls: ['./removetrain.component.css']
})
export class RemovetrainComponent {

  constructor (private http: HttpClient) {}

  trainNumber = '';
  departureDate :any;

  proceed(){

    console.log("clicked");
    

    let url3 = 'http://localhost:9090/admin/removetrain/'+this.trainNumber

    this.http.delete(url3).subscribe(
      (response) =>{
        console.log(response);
        window.alert(response);
      },
      (error) =>{
        console.log(error);
        window.alert(error.error.text)
      }
      
    )

  }

}
