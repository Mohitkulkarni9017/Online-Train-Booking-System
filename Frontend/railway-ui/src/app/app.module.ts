import { NgModule, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './services/login/login.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './services/header/header.component';
import { FooterComponent } from './services/footer/footer.component';
import { HomeComponent } from './services/home/home.component';
import { PaymentComponent } from './services/payment/payment.component';
import { BookingComponent } from './services/booking/booking.component';
import { SignupComponent } from './services/signup/signup.component';
import { JwtInterceptor } from './jwt.interceptor';
import { AuthServiceService } from './auth-service.service';
import { AdminComponent } from './services/admin/admin.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { RemovetrainComponent } from './services/removetrain/removetrain.component';
import { AddtrainComponent } from './services/addtrain/addtrain.component';
import { ReactiveFormsModule } from '@angular/forms'; 


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    PaymentComponent,
    BookingComponent,
    SignupComponent,
    AdminComponent,
    AdminloginComponent,
    RemovetrainComponent,
    AddtrainComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthServiceService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule{
  
}
