import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/auth-service.service';

export interface Train {
  number: string;
  name: string;
  source: string;
  destination: string;
  startArrivalTime: string;
  endArrivalTime: string;
  date: Date;
  avsl: number;
  av2a: number;
  av3a: number;
  slFare: number;
  twoACFare: number;
  threeACFare: number;
  showProceedButton?: boolean;
  selectedTicketType?: string;
  fare?: number;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  source: string = '';
  destination: string = '';
  date!: Date;
  trains: Train[] = [];
  date1: string = '';
  errormsg: string = '';
  show: boolean = false;

  temp: string = '';

  sourceOptions: string[] = ['Akhurdi', 'CSMT', 'Dadar', 'Daund', 'Pune', 'ShivajiNagar', 'Solapur', 'Thane'];
  destinationOptions: string[] = ['Akhurdi', 'CSMT', 'Dadar', 'Daund', 'Pune', 'ShivajiNagar', 'Solapur', 'Thane'];

  constructor(private http: HttpClient, private router: Router, private service: AuthServiceService) {}

  searchTrains() {
    this.show = false;
    if (this.source === this.destination) {
      this.errormsg = 'Source and destination cannot be the same';
      this.show = true;
      return;
    }

    const url = 'http://localhost:9191/gettrains';
    

    const body = {
      source: this.source,
      destination: this.destination,
      date: this.date1
    };

    this.http.post<Train[]>(url, body, { observe: 'response' }).subscribe(
      (response: HttpResponse<Train[]>) => {
        if (response.status === 200) {
          this.trains = response.body?.map(train => ({
            ...train,
            showProceedButton: false,
            selectedTicketType: '',
            fare: 0
          })) ?? [];
          
          
        } else {
          this.errormsg = 'Error retrieving train details';
          this.show = true;
        }
      },
      error => {
        this.errormsg = 'Error retrieving train details';
        this.show = true;
      }
    );
  }

  getCurrentDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  onDateChange(event: any) {
    const selectedDate = new Date(event.target.value);
    const year = selectedDate.getFullYear();
    const month = ('0' + (selectedDate.getMonth() + 1)).slice(-2);
    const day = ('0' + selectedDate.getDate()).slice(-2);
    this.date1 = `${year}-${month}-${day}`;
    this.date = new Date(this.date1);
  }

  showFare(train: Train, ticketType: string) {
    train.showProceedButton = true;
    train.selectedTicketType = ticketType;

    switch (ticketType) {
      case 'avsl':
        train.fare = train.slFare;
        break;
      case 'av2a':
        train.fare = train.twoACFare;
        break;
      case 'av3a':
        train.fare = train.threeACFare;
        break;
    }
  }

  proceed(train: Train) {
    let token2 = localStorage.getItem("JWT_TOKEN");
    if(token2!=null) {
      this.router.navigate(['book'], {
        queryParams: {
          trainNo: train.number,
          trainName: train.name,
          type: train.selectedTicketType,
          availableTickets: this.getAvailableTickets(train),
          departureDate: this.date,
          source: train.source,
          destination: train.destination,
          fare: train.fare
        }
      });
    }else{
      this.router.navigate(['/login']);
    }
  }

  getAvailableTickets(train: Train): number {
    switch (train.selectedTicketType) {
      case 'avsl':
        return train.avsl;
      case 'av2a':
        return train.av2a;
      case 'av3a':
        return train.av3a;
      default:
        return 0;
    }
  }
}



