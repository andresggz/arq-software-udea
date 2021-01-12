import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CareerAdminService } from './career-admin.service'; 
import { CareerSaveRequest } from './model/career-save-request';

describe('CareerAdminService', () => {
  let service: CareerAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(CareerAdminService);

     

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should retrieve a career created via get'), () => {

    const careerToCreate: CareerSaveRequest = {name: 'Desarrollo y programacion',
                      description: 'Cualquier descripcion'}

    service.create(careerToCreate).subscribe(res => 
      {
        let roadmapCreatedId = res.headers.get('location').split('/')[4];

        service.findByParameters(0).subscribe(res2 => {

          //expect(res2.result[0].name).toEqual('Desarrollo y programacion');
          expect(roadmapCreatedId).toBeGreaterThan(0);
        })
      });
  }

});
