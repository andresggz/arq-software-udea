import { TestBed } from '@angular/core/testing';

import { CareerPageService } from './career-page.service';

describe('CareerPageService', () => {
  let service: CareerPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CareerPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
