import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VehicleSaveResponse } from 'src/app/pages/vehicle-page/model/vehicle-save-response-model';
import { VehicleSaveRequest } from './model/vehicle-save-request';

@Injectable({
  providedIn: 'root'
})
export class VehicleAdminService {

  constructor(private readonly http: HttpClient) { }

  create(vehicleToCreate: VehicleSaveRequest): Observable<HttpResponse<object>>{
    return this.http.post('http://localhost:8080/api/v1/vehicles', vehicleToCreate, {observe: 'response'});
  }

  findVehicleByUrl(vehicleUrl: string): Observable<VehicleSaveResponse> {
    console.log(vehicleUrl);
    return this.http.get<VehicleSaveResponse>(`http://localhost:8080${vehicleUrl}`);
  }

  linkVehicleToFleet(fleetId: String, vehicleCreatedId: string) {
    let url = `http://localhost:8080/api/v1/fleets/${fleetId}/vehicles`;
    console.log(url);
    let vehicleToAddId: number = +vehicleCreatedId;
    return this.http.patch(url, {vehicleId: vehicleToAddId});
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/vehicles/${id}`);
  }

  uploadFile(formData: FormData, url: string): Observable<string[]>{
    return this.http.post<string[]>('http://localhost:8080'+url, formData);
  }
}
