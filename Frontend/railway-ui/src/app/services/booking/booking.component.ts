import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

export interface PassengerListobj {
  trainNo : string;
  trainName : string;
  type : string;
  prefenence  : string;
  name: string;
  age: number;
  gender: string;
  departureDate : string | undefined;
}

export interface Passenger {
  name: string;
  age: number;
  gender: string;
  preference: string;
}

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {
  trainNo: string = '';
  trainName: string = '';
  type: string = '';
  availableTickets: number = 0;
  departureDate: Date | undefined;
  source: string = '';
  destination: string = '';
  fare:number=0;
  passengers: Passenger[] = [];
  passengerlist: PassengerListobj[] = [];
  
  maxPassengers: number = 7;

  name1:string='';
  email:string = '';
  number: number | undefined;

  bookingForm: FormGroup;
 

  constructor(private route: ActivatedRoute, private router: Router,private formBuilder: FormBuilder) {
    this.route.queryParams.subscribe(params => {
      this.trainNo = params['trainNo'];
      this.trainName = params['trainName'];
      this.type = params['type'];
      this.availableTickets = params['availableTickets'];
      this.departureDate = new Date(params['departureDate']);
      this.source = params['source'];
      this.destination = params['destination'];
      this.fare = params['fare'];
    });

    this.bookingForm = this.formBuilder.group({
      // Other form controls here
      name1: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      number: ['', [Validators.required, Validators.pattern('[0-9]{10}')]]
    });
    
  }

  addPassenger() {
    if (this.passengers.length < this.maxPassengers) {
      this.passengers.push({
        name: '',
        age: 0,
        gender: '',
        preference: ''
      });
    }
  }

  calculateTotalAmount(): number {
    const numberOfPassengers = this.passengers.length;
    const totalAmount = this.fare * numberOfPassengers;
    return totalAmount;
  }
  
  removePassenger(index: number) {
    this.passengers.splice(index, 1);
  }

  book() {
    const hasPassengerDetails = this.passengers.length > 0 && this.passengers.every(passenger =>
      passenger.name.trim() !== '' &&
      passenger.age > 0 &&
      passenger.gender.trim() !== '' &&
      passenger.preference.trim() !== ''
    );
  
    if (this.bookingForm.valid && hasPassengerDetails) {
      console.log('Booking successful!');
  
      const orderRequest = {
        customerName: this.name1,
        email: this.email,
        phoneNumber: this.number,
        amount: this.calculateTotalAmount() 
      };
      this.makeList();
      this.router.navigate(['payment'], {
        queryParams: { 
          orderRequest: JSON.stringify(orderRequest),
          passengerlist: JSON.stringify(this.passengerlist) 
        } 
      });
    } else {
      console.log('Please provide valid passenger details and contact information.');
      alert('Please provide valid passenger details and contact information.');
    }
  }
  
  
  // book() {
  //   const hasPassengerDetails = this.passengers.length > 0 && this.passengers.every(passenger =>
  //     passenger.name.trim() !== '' &&
  //     passenger.age > 0 &&
  //     passenger.gender.trim() !== '' &&
  //     passenger.preference.trim() !== ''
  //   );
  
  //   const hasContactDetails = this.email.trim() !== '' && this.number !== undefined && this.number.toString().trim() !== '';
  
  //   if (hasPassengerDetails && hasContactDetails) {
    
  //     console.log('Booking successful!');

  //     const orderRequest = {
  //       customerName: this.name1,
  //       email: this.email,
  //       phoneNumber: this.number,
  //       amount: this.calculateTotalAmount() 
  //     };
  //     this.makeList();
  //     this.router.navigate(['payment'], {
  //        queryParams: { 
  //         orderRequest: JSON.stringify(orderRequest),
  //         passengerlist: JSON.stringify(this.passengerlist) 
  //       } });

      
  //   } else {
  //     console.log('Please provide valid passenger details and contact information.');
  //     alert('Please provide valid passenger details and contact information.');
  //   }
  // }
  
  makeList() {
    this.passengerlist = [];
    const formattedDepartureDate = this.departureDate?.toISOString().split('T')[0];

    for (let i = 0; i < this.passengers.length; i++) {
      const passenger: Passenger = this.passengers[i];
      const passengerListObj: PassengerListobj = {
        trainNo: this.trainNo,
        trainName: this.trainName,
        type: this.getTypeLabel(this.type),
        prefenence: passenger.preference,
        name: passenger.name,
        age: passenger.age,
        gender: passenger.gender,
        departureDate: formattedDepartureDate
      };
      this.passengerlist.push(passengerListObj);
    }
  }

  getTypeLabel(type: string): string {
    if (type === 'avsl') {
      return 'Sleeper';
    } else if (type === 'av2a') {
      return '2 Tier AC';
    } else if (type === 'av3a') {
      return '3 Tier AC';
    } else {
      return '';
    }
  }
}
