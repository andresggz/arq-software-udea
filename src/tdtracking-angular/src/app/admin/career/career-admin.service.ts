import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePagination } from 'src/app/home/model/response-pagination-model';
import { CareerSaveRequest } from './model/career-save-request';

@Injectable({
  providedIn: 'root'
})
export class CareerAdminService {

  constructor(private readonly http: HttpClient) { }

  create(careerToCreate: CareerSaveRequest): Observable<HttpResponse<object>> {
    return this.http.post('http://localhost:8080/api/v1/careers', careerToCreate, {observe: 'response'});
  }


  findByParameters(page: number): Observable<ResponsePagination> {
    return this.http.get<ResponsePagination>(`http://localhost:8080/api/v1/careers?page=${page}&size=4`);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/careers/${id}`);
  }

}
