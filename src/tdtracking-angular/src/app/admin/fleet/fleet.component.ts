import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FleetListResponse } from 'src/app/home/model/fleet-list.model';
import { FleetAdminService } from './fleet-admin.service';
import { FleetSaveRequest } from './model/fleet-save-request';


@Component({
  selector: 'app-fleet',
  templateUrl: './fleet.component.html',
  styleUrls: ['./fleet.component.css']
})
export class FleetComponent implements OnInit {

  fleetsList: FleetListResponse[];

  createFleetForm: FormGroup;

  actualPage: number;

  constructor(private _builder: FormBuilder, private readonly fleetAdminService: FleetAdminService) {
    this.createFleetForm = this._builder.group(
      {
        name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(45)])],
        description: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(45)])]
      }
    )
   }

  ngOnInit(): void {
    this.findFleets(0);
  }

  createFleet(value){
    const fleetToCreate: FleetSaveRequest = {
      name: value.name,
      description: value.description
    }

    this.fleetAdminService.create(fleetToCreate).subscribe(x => {
      this.createFleetForm.reset(),
    this.findFleets(this.actualPage)
    });
    
  }

  findFleets(pageToRetrive: number){
    this.fleetAdminService.findByParameters(pageToRetrive)
            .subscribe(responsePagination => {
                this.fleetsList = responsePagination.result;
                this.actualPage = responsePagination.page;
                console.log(responsePagination);
            })
  }

  nextPage(){
    this.findFleets(this.actualPage +1);
  }

  previousPage(){
    this.findFleets(this.actualPage -1);
  }

  findFleetsPageZero(){
    this.findFleets(0);
  }

  deleteById(id: number){
    this.fleetAdminService.deleteById(id).subscribe(x => {
      this.findFleets(0);
    })
  }
}
