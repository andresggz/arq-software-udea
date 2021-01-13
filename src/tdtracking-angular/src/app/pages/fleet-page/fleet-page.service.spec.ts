import { TestBed } from '@angular/core/testing';
import { FleetPageService } from './fleet-page.service';

describe('FleetPageService', () => {
  let service: FleetPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FleetPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
