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
import { FleetComponent } from './admin/fleet/fleet.component';
import { VehicleComponent } from './admin/vehicle/vehicle.component';
import { FleetPageComponent } from './pages/fleet-page/fleet-page.component';
import { VehiclePageComponent } from './pages/vehicle-page/vehicle-page.component';
import { FleetAdminService } from './admin/fleet/fleet-admin.service';
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
    FleetComponent,
    VehicleComponent,
    FleetPageComponent,
    VehiclePageComponent,
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
  providers: [FleetAdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
