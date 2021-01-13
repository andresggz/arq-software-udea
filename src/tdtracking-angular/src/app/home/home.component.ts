import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from './home-service.service';
import { FleetListResponse } from './model/fleet-list.model';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

    fleetsList: FleetListResponse[];

    model = {
        left: true,
        middle: false,
        right: false
    };

    focus;
    focus1;
    constructor(private readonly homeService: HomeServiceService) { }

    ngOnInit() {
        //this.fleetsList = this.homeService.getFleets();
        this.homeService.getFleets()
            .subscribe(responsePagination => {
                this.fleetsList = responsePagination.result;
                console.log(responsePagination);
            })
    }
}
