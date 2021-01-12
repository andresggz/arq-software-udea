import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { CareerComponent } from './admin/career/career.component';
import { CareerPageComponent } from './pages/career-page/career-page.component';
import { RoadmapPageComponent } from './pages/roadmap-page/roadmap-page.component';
import { RoadmapComponent } from './admin/roadmap/roadmap.component';

const routes: Routes =[
    { path: 'home',             component: HomeComponent },
    { path: 'user-profile',     component: ProfileComponent },
    { path: 'register',           component: SignupComponent },
    { path: 'landing',          component: LandingComponent },
    { path: 'login',          component: LoginComponent },
    {path: 'carrera/:id', component: CareerPageComponent},
    {path: 'ruta/:id', component: RoadmapPageComponent},
    {path: 'admin/carreras', component: CareerComponent},
    {path: 'admin/carreras/:id', component: RoadmapComponent},
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
