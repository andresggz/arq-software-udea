import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LandingComponent } from './landing/landing.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';

import { HomeModule } from './home/home.module';
import { LoginComponent } from './login/login.component';
import { CareerComponent } from './admin/career/career.component';
import { RoadmapComponent } from './admin/roadmap/roadmap.component';
import { CareerPageComponent } from './pages/career-page/career-page.component';
import { RoadmapPageComponent } from './pages/roadmap-page/roadmap-page.component';
import { CareerAdminService } from './admin/career/career-admin.service';
import { UploadComponent } from './admin/upload/upload.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LandingComponent,
    ProfileComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    CareerComponent,
    RoadmapComponent,
    CareerPageComponent,
    RoadmapPageComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    HomeModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CareerAdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
