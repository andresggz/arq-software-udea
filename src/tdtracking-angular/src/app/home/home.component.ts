import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from './home-service.service';
import { CareerListResponse } from './model/career-list.model';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

    careersList: CareerListResponse[];

    model = {
        left: true,
        middle: false,
        right: false
    };

    focus;
    focus1;
    constructor(private readonly homeService: HomeServiceService) { }

    ngOnInit() {
        //this.careersList = this.homeService.getCareers();
        this.homeService.getCareers()
            .subscribe(responsePagination => {
                this.careersList = responsePagination.result;
                console.log(responsePagination);
            })
    }
}
