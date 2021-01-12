import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoadmapSaveResponse } from 'src/app/pages/roadmap-page/model/roadmap-save-response-model';
import { RoadmapSaveRequest } from './model/roadmap-save-request';

@Injectable({
  providedIn: 'root'
})
export class RoadmapAdminService {

  constructor(private readonly http: HttpClient) { }

  create(roadmapToCreate: RoadmapSaveRequest): Observable<HttpResponse<object>>{
    return this.http.post('http://localhost:8080/api/v1/roadmaps', roadmapToCreate, {observe: 'response'});

  }

  findRoadmapByUrl(roadmapUrl: string): Observable<RoadmapSaveResponse> {
    console.log(roadmapUrl);
    return this.http.get<RoadmapSaveResponse>(`http://localhost:8080${roadmapUrl}`);
  }

  linkRoadmapToCareer(careerId: String, roadmapCreatedId: string) {
    let url = `http://localhost:8080/api/v1/careers/${careerId}/roadmaps`;
    console.log(url);
    let roadmapToAddId: number = +roadmapCreatedId;
    return this.http.patch(url, {roadmapId: roadmapToAddId});
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/roadmaps/${id}`);
  }

  uploadFile(formData: FormData, url: string): Observable<string[]>{

    return this.http.post<string[]>('http://localhost:8080'+url, formData);
  }
}
