import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CareerPageService } from './career-page.service';
import { CareerSaveResponse} from './model/career-response-model';
import { RoadmapListResponse } from './model/roadmap-list-model';

@Component({
  selector: 'app-career-page',
  templateUrl: './career-page.component.html',
  styleUrls: ['./career-page.component.css']
})
export class CareerPageComponent implements OnInit {

  careerId: String;

  roadmapsLinks: string[] = [];

  roadmaps: RoadmapListResponse[] = [];

  careerInfo: CareerSaveResponse;

  constructor(private readonly careerPageService: CareerPageService, private readonly route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.careerId = params.get('id');
      this.getCareerInfo();
    })
  }

  getCareerInfo(): void{
   this.careerPageService.getCareerById(this.careerId)
    .subscribe(career => {
      this.careerInfo = career; 
        this.roadmapsLinks = career.roadmaps;
      
      this.getRoadmaps();
    });
  }

  getRoadmaps(){

    for(let link of this.roadmapsLinks){
      this.careerPageService.getRoadmap(link).subscribe(roadmap =>{
        console.log(roadmap);
         this.roadmaps.push(roadmap)});
    }
    
  }
  
}
