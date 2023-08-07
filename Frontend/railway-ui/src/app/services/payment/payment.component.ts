import { Component, HostListener } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderServiceService } from './order-service.service';
import { ActivatedRoute } from '@angular/router';
import { Renderer2 } from '@angular/core';
import axios, { Axios } from 'axios';


declare var Razorpay: any;


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  TOKEN_KEY: string = "JWT_TOKEN";
  form: any = {};
  paymentId: string = '';
  error: string = '';
  response: any = {};

  options = {
    key: '',
    amount: '',
    name: 'Railway Booking System',
    description: 'CaseStudy',
    image: 'assets/logo.png',
    order_id: '',
    paymentstatus : '',
    "handler": function (response: any){
    var event = new CustomEvent("payment.success", 
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );	  
      window.dispatchEvent(event);
    },
    prefill: {
      name: '',
      email: '',
      contact: ''
    },
    notes: {
      address: ''
    },
    theme: {
      color: '#3399cc'
    }
  };
  orderRequest: any;
  show :boolean =false
  passengers: any;
  passengerlist: any;

  constructor(
    private http: HttpClient, 
    private orderService: OrderServiceService,
    private route: ActivatedRoute,
    private renderer: Renderer2
    )
     {}

 

ngOnInit() {
  this.orderRequest = JSON.parse(this.route.snapshot.queryParamMap.get('orderRequest') || '');
  this.passengerlist = JSON.parse(this.route.snapshot.queryParamMap.get('passengerlist') || ''); 
  console.log("payment ng on it");
  
  if (this.orderRequest) {
    this.form = this.orderRequest;
    console.log(this.orderRequest);
    this.onSubmit();
  }
  
} 

 

  onSubmit(): void {
    this.paymentId = '';
    this.error = '';
    console.log(localStorage.getItem("JWT_TOKEN"));
    this.orderService.createOrder(this.form).subscribe(
      (data: any) => {
        this.options.key = data.secretId;
        this.options.order_id = data.razorpayOrderId;
        this.options.amount = data.applicationFee;
        this.options.prefill.name = 'Train Booking';
        this.options.prefill.email = 'codingworld@gmail.com';
        console.log("payment")
        this.options.paymentstatus = "";
        this.options.prefill.contact = '9999999999';

        if (data.pgName === 'razor2') {
          this.options.image = '';
          var rzp1 = new Razorpay(this.options);
          console.log(this.options);
          
          rzp1.open();
           // Assuming `rzp1` is your Razorpay instance
           rzp1.on('payment.success', (response: any) => {
            console.log(response);
            this.onPaymentSuccess(response);
            console.log("after payment");
          });
        } else {
          var rzp2 = new Razorpay(this.options);
          rzp2.open();
           rzp2.on('payment.success', (response: any) => {
            console.log(response);
            this.onPaymentSuccess(response);
            console.log("after payment");
          });
          
          console.log(this.options);
          
        }
        rzp1.on('payment.failed', (response: any) => {
          console.log(response);
          console.log(this.options);
          this.options.paymentstatus = "fail";
          console.log(this.options.paymentstatus);
          
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);
          this.error = response.error.reason;
        });
      },
      (err: any) => {
        this.error = err.error.message;
        console.log("error");
        
      }
    );
    
  }

  @HostListener('window:payment.success', ['$event']) 
  onPaymentSuccess(event: any): void {
    console.log(event.detail);
    console.log("payment success")
   
    console.log(this.passengerlist);

    let token1 = 'Bearer '+ localStorage.getItem(this.TOKEN_KEY)
    console.log(token1);
    
    

  //   axios.post('http://localhost:9090/book/tickit',this.passengerlist, { headers })
  // .then(response => {
  //   // Handle the response from the backend
  //   console.log(response.data);
  // })
  // .catch(error => {
  //   // Handle any errors that occur during the request
  //   console.error(error);
  // });
  // , {headers: header , responseType: 'text' as 'json' }

    const header = new HttpHeaders().set('Authorization',token1);
    this.http.post<any>('http://localhost:9090/book/tickit', this.passengerlist).subscribe(
    (response: any) => {
      console.log("Booking success on backend:", response);
      this.show = true;
      this.response = response;
    },
    (error: any) => {
      console.log(header);
      console.log("Error while booking on backend:", error);
    }
  );
  }
  }