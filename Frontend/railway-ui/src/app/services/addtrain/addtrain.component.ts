import { Component } from '@angular/core';
import { DepartureDate, TrainDetails, TrainRoute } from './train-details.model';
import { TrainService } from '../train.service';
import { window } from 'rxjs';

@Component({
  selector: 'app-addtrain',
  templateUrl: './addtrain.component.html',
  styleUrls: ['./addtrain.component.css']
})
export class AddtrainComponent {

  division = ['Mumbai', 'Pune', 'Solapur'];
  station = ['Akhurdi', 'CSMT', 'Dadar', 'Daund', 'Pune', 'ShivajiNagar', 'Solapur', 'Thane'];

   myMap = new Map<string, string>([
    ["Akhurdi", "Pune"],
    ["CSMT", "Mumbai"],
    ["Dadar", "Mumbai"],
    ["Daund", "Pune"],
    ["Pune", "Pune"],
    ["ShivajiNagar", "Pune"],
    ["Solapur", "Solapur"],
    ["Thane", "Mumbai"]
]);

  formSubmitted = false;
  divs ='';

  trainDetails: TrainDetails = new TrainDetails('', '', 0, 0, 0, [
    new TrainRoute('', '', '', '', '', '', '2023-01-07', '', '', ''),
    new TrainRoute('', '', '', '', '', '', '2023-01-07', '', '', ''),
    new TrainRoute('', '', '', '', '', '', '2023-01-07', '', '', '')
  ], []);
  
  additionalSchedule: DepartureDate[] = [];
  
  constructor(private trainService: TrainService) {}
  
  isFormValid(): boolean {
    let isValid = true;

    

    // Check required fields in trainDetails
    if (
      !this.trainDetails.trainNo ||
      !this.trainDetails.trainName ||
      this.trainDetails.avsl === 0 ||
      this.trainDetails.av2a === 0 ||
      this.trainDetails.av3a === 0
    ) {
      isValid = false;
    }

    // Check required fields in trainRoutes
    for (const route of this.trainDetails.trainRoutes) {
      if (
        !route.source ||
        !route.destination ||
        !route.startArrivalTime ||
        !route.endArrivalTime ||
        !route.slFare ||
        !route.twoACFare ||
        !route.threeACFare
      ) {
        isValid = false;
        break;
      }
    }

    // Check required fields in additionalSchedule
    let hasValidDepartureDate = false;
    for (const schedule of this.additionalSchedule) {
      if (schedule.departureDate) {
        hasValidDepartureDate = true;
        break;
      }
    }

    if (!hasValidDepartureDate) {
      isValid = false;
    }

    return isValid;
  }

  

  

  onSubmit() {

    this.formSubmitted = true;

    // Check if any required fields are empty
    if (this.isFormValid()) {

      for (const route of this.trainDetails.trainRoutes) {
        const divSource = this.myMap.get(route.source);
        const divDest = this.myMap.get(route.destination);
        route.divSource = divSource ?? '';
        route.divDest = divDest ?? '';
      }
      this.trainDetails.trainSchedule = this.additionalSchedule;
      this.trainService.addTrain(this.trainDetails).subscribe(
        response => {
          console.log('Train added successfully:', response);
          alert(response);
          // Clear the form or show a success message as needed.
        },
        error => {
          console.error('Error while adding train:', error);
          // Handle error scenarios here.
        }
      );
    }
  }
  
  addSchedule() {
    this.additionalSchedule.push(new DepartureDate(''));
  }
  
  removeSchedule(index: number) {
    if (index >= 0 && index < this.trainDetails.trainSchedule.length) {
      this.trainDetails.trainSchedule.splice(index, 1);
    }
  }
  
  removeAdditionalSchedule(index: number) {
    if (index >= 0 && index < this.additionalSchedule.length) {
      this.additionalSchedule.splice(index, 1);
    }
  }
}




