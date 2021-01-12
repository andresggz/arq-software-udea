import { TestBed } from '@angular/core/testing';

import { RoadmapAdminService } from './roadmap-admin.service';

describe('RoadmapAdminService', () => {
  let service: RoadmapAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoadmapAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
