export class TrainDetails {
    constructor(
      public trainNo: string,
      public trainName: string,
      public avsl: number,
      public av2a: number,
      public av3a: number,
      public trainRoutes: TrainRoute[],
      public trainSchedule: DepartureDate[]
    ) {}
  }
  
  export class TrainRoute {
    constructor(
      public divSource: string,
      public divDest: string,
      public source: string,
      public destination: string,
      public startArrivalTime: string,
      public endArrivalTime: string,
      public date: string,
      public slFare: string,
      public twoACFare: string,
      public threeACFare: string
    ) {}
  }
  
  export class DepartureDate {
    constructor(public departureDate: string) {}
  }
  