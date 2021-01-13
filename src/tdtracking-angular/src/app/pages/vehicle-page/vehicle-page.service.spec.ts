import { TestBed } from '@angular/core/testing';

import { VehiclePageService } from './vehicle-page.service';

describe('VehiclePageService', () => {
  let service: VehiclePageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehiclePageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
