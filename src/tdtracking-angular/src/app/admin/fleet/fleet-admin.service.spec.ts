import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FleetAdminService } from './fleet-admin.service'; 
import { FleetSaveRequest } from './model/fleet-save-request';

describe('FleetAdminService', () => {
  let service: FleetAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(FleetAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should retrieve a fleet created via get'), () => {
    const fleetToCreate: FleetSaveRequest = {name: 'Desarrollo y programacion',
                      description: 'Cualquier descripcion'}

    service.create(fleetToCreate).subscribe(res => 
      {
        let vehicleCreatedId = res.headers.get('location').split('/')[4];

        service.findByParameters(0).subscribe(res2 => {

          //expect(res2.result[0].name).toEqual('Desarrollo y programacion');
          expect(vehicleCreatedId).toBeGreaterThan(0);
        })
      });
  }

});
