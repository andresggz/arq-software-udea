import { TestBed } from '@angular/core/testing';
import { VehicleAdminService } from './vehicle-admin.service';

describe('VehicleAdminService', () => {
  let service: VehicleAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehicleAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
