import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './services/home/home.component';
import { LoginComponent } from './services/login/login.component';
import { BookingComponent } from './services/booking/booking.component';
import { PaymentComponent } from './services/payment/payment.component';
import { SignupComponent } from './services/signup/signup.component';
import { AdminComponent } from './services/admin/admin.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { RemovetrainComponent } from './services/removetrain/removetrain.component';
import { AddtrainComponent } from './services/addtrain/addtrain.component';
import { AuthGuardService } from './auth-guard.service';

const routes: Routes = [
  {path : '', component : HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'book', component : BookingComponent, canActivate: [AuthGuardService]},
  {path : 'payment', component : PaymentComponent, canActivate: [AuthGuardService]},
  {path: 'signup', component : SignupComponent},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuardService]},
  {path: 'adminlogin', component: AdminloginComponent, canActivate: [AuthGuardService]},
  {path: 'removetrain', component: RemovetrainComponent, canActivate: [AuthGuardService]},
  {path: 'addtrain', component: AddtrainComponent, canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
