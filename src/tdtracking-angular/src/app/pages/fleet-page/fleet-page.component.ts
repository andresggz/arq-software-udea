import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FleetPageService } from './fleet-page.service';
import { FleetSaveResponse} from './model/fleet-response-model';
import { VehicleListResponse } from './model/vehicle-list-model';

@Component({
  selector: 'app-fleet-page',
  templateUrl: './fleet-page.component.html',
  styleUrls: ['./fleet-page.component.css']
})
export class FleetPageComponent implements OnInit {

  fleetId: String;

  vehiclesLinks: string[] = [];

  vehicles: VehicleListResponse[] = [];

  fleetInfo: FleetSaveResponse;

  constructor(private readonly fleetPageService: FleetPageService, private readonly route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.fleetId = params.get('id');
      this.getFleetInfo();
    })
  }

  getFleetInfo(): void{
   this.fleetPageService.getFleetById(this.fleetId)
    .subscribe(fleet => {
      this.fleetInfo = fleet; 
        this.vehiclesLinks = fleet.vehicles;
      
      this.getVehicles();
    });
  }

  getVehicles(){
    for(let link of this.vehiclesLinks){
      this.fleetPageService.getVehicle(link).subscribe(vehicle =>{
        console.log(vehicle);
         this.vehicles.push(vehicle)});
    }
  }
}
