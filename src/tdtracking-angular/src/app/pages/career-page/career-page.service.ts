import { Injectable } from '@angular/core';
import {  CareerSaveResponse } from './model/career-response-model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RoadmapListResponse } from './model/roadmap-list-model';

@Injectable({
  providedIn: 'root'
})
export class CareerPageService {

  constructor(private readonly http: HttpClient) { }

  getCareerById(id: String): Observable<CareerSaveResponse>{

    return this.http.get<CareerSaveResponse>(`http://localhost:8080/api/v1/careers/${id}`);
  }

  getRoadmap(url: string): Observable<RoadmapListResponse>{
    return this.http.get<RoadmapListResponse>(`http://localhost:8080${url}`);
  }
}
