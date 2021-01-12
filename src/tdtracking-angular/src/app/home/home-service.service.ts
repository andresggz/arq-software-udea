import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CareerListResponse } from './model/career-list.model';
import { HttpClient } from '@angular/common/http';
import { ResponsePagination } from './model/response-pagination-model';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {


  
  constructor(private readonly http: HttpClient) { 

  }

  getCareers(): Observable<ResponsePagination>{
    
    return this.http.get<ResponsePagination>('http://localhost:8080/api/v1/careers');

  }
}
