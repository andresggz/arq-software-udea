import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TripList } from './model/trip-list-model';
import { VehicleSaveResponse } from './model/vehicle-save-response-model';
import { VehiclePageService } from './vehicle-page.service';

@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.css']
})
export class VehiclePageComponent implements OnInit {

  vehicleId: String;

  vehicleInfo: VehicleSaveResponse;

  tripsLinks: string[];

  trips: TripList[] = [];

  constructor(private readonly vehiclePageService: VehiclePageService,
    private readonly route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.vehicleId = params.get('id');
      this.getVehicleInfo();
    });

  }

  getVehicleInfo(): void{
    this.vehiclePageService.getVehicleById(this.vehicleId)
    .subscribe(vehicle => {
      this.vehicleInfo = vehicle;
      this.tripsLinks = vehicle.trips;
      this.getTrips();
    });
  }

  getTrips(){

    for(let link of this.tripsLinks){

      this.vehiclePageService.getTrip(link)
        .subscribe(course => this.trips.push(course));
    }
  }
}
