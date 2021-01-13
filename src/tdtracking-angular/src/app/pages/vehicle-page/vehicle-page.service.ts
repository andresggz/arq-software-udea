import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TripList } from './model/trip-list-model';
import { VehicleSaveResponse } from './model/vehicle-save-response-model';

@Injectable({
  providedIn: 'root'
})
export class VehiclePageService {


  constructor(private readonly http: HttpClient) { }

  getVehicleById(id: String): Observable<VehicleSaveResponse> {
    return this.http.get<VehicleSaveResponse>(`http://localhost:8080/api/v1/vehicles/${id}`);
  }

  getTrip(url: string): Observable<TripList>{
    return this.http.get<TripList>(`http://localhost:8080${url}`);
  }
}
