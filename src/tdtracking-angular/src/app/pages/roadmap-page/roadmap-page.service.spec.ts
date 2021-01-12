import { TestBed } from '@angular/core/testing';

import { RoadmapPageService } from './roadmap-page.service';

describe('RoadmapPageService', () => {
  let service: RoadmapPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoadmapPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
