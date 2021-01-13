import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FleetPageService } from 'src/app/pages/fleet-page/fleet-page.service';
import { FleetSaveResponse } from 'src/app/pages/fleet-page/model/fleet-response-model';
import { VehicleSaveResponse } from 'src/app/pages/vehicle-page/model/vehicle-save-response-model';
import { VehicleSaveRequest } from './model/vehicle-save-request';
import { VehicleAdminService } from './vehicle-admin.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  uploadedFile: File;

  fleetId: String

  fleetInfo: FleetSaveResponse;

  createVehicleForm: FormGroup;

  vehiclesList: VehicleSaveResponse[] = [];

  page: Number;

  constructor(private readonly route: ActivatedRoute, private readonly fleetPageService: FleetPageService, private _builder: FormBuilder,
    private readonly vehicleAdminService: VehicleAdminService) { 
    this.createVehicleForm = this._builder.group(
      {
        name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(45)])],
        description: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(700)])],
        detail: ['', Validators.compose([Validators.required, Validators.minLength(3)])]
      }
    )
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.fleetId = params.get('id');
      this.getFleetInfo();
    })
  }

  createVehicle(value){
    const vehicleToCreate: VehicleSaveRequest = {
      name: value.name,
      description: value.description,
      licensePlate: value.licensePlate,
      model: value.model,
      brand: value.brand
    }

    this.vehicleAdminService.create(vehicleToCreate).subscribe(x =>{
       let vehicleCreatedId = x.headers.get('location').split('/')[4];
       this.vehicleAdminService.linkVehicleToFleet(this.fleetId, vehicleCreatedId).subscribe(y => {
         console.log(y);
         this.vehiclesList = [];
         this.getFleetInfo();
       });
    } );
    this.createVehicleForm.reset();
  }

  getFleetInfo(){
    this.fleetPageService.getFleetById(this.fleetId).subscribe(res => {
      this.fleetInfo = res;
      this.vehiclesList = [];
      for(let vehicleLink of this.fleetInfo.vehicles){
        this.vehicleAdminService.findVehicleByUrl(vehicleLink).subscribe(
          vehicle => this.vehiclesList.push(vehicle));
      }
    
    });
  }

  deleteById(id: number){
    this.vehicleAdminService.deleteById(id).subscribe(x => {
      this.getFleetInfo();
    })
  }

  fileChange(element) {
    this.uploadedFile = element.target.files[0];
  }

  upload() {
    let formData = new FormData();
      formData.append("file",this.uploadedFile);
    this.vehicleAdminService.uploadFile(formData, '/api/v1/vehicles/upload-sheets').subscribe((res)=> 
    {
      for(let index in res){
        let vehicleCreatedUrl = res[index];

        let vehicleCreatedId = vehicleCreatedUrl.split('/')[4];
      this.vehicleAdminService.linkVehicleToFleet(this.fleetId, vehicleCreatedId).subscribe(y => {
      });

      }
      this.getFleetInfo();
    });
    }
}
