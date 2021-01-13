import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FleetSaveResponse } from './model/fleet-response-model';
import { VehicleListResponse } from './model/vehicle-list-model';

@Injectable({
  providedIn: 'root'
})
export class FleetPageService {

  constructor(private readonly http: HttpClient) { }

  getFleetById(id: String): Observable<FleetSaveResponse>{

    return this.http.get<FleetSaveResponse>(`http://localhost:8080/api/v1/fleets/${id}`);
  }

  getVehicle(url: string): Observable<VehicleListResponse>{
    return this.http.get<VehicleListResponse>(`http://localhost:8080${url}`);
  }
}
