import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TrainDetails } from './addtrain/train-details.model';

@Injectable({
  providedIn: 'root'
})
export class TrainService {


  private baseUrl = 'http://localhost:9090/admin/add'; // Replace this URL with your backend API URL

  constructor(private http: HttpClient) { }

  addTrain(trainDetails: TrainDetails): Observable<any> {
    console.log(trainDetails);
    return this.http.post<any>(this.baseUrl, trainDetails,{responseType: 'test' as 'json'});
  }
}
