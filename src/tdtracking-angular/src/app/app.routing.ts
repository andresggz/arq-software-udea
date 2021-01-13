import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { FleetComponent } from './admin/fleet/fleet.component';
import { FleetPageComponent } from './pages/fleet-page/fleet-page.component';
import { VehicleComponent } from './admin/vehicle/vehicle.component';
import { VehiclePageComponent } from './pages/vehicle-page/vehicle-page.component';

const routes: Routes =[
    { path: 'home', component: HomeComponent },
    { path: 'user-profile', component: ProfileComponent },
    { path: 'register', component: SignupComponent },
    { path: 'landing', component: LandingComponent },
    { path: 'login', component: LoginComponent },
    { path: 'flotas/:id', component: FleetPageComponent },
    { path: 'vehiculos/:id', component: VehiclePageComponent },
    { path: 'admin/flotas', component: FleetComponent },
    { path: 'admin/flotas/:id', component: VehicleComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
      useHash: true
    })
  ],
  exports: [
  ],
})

export class AppRoutingModule { }
