import { TestBed } from '@angular/core/testing';

import { ImpotService } from './impot.service';

describe('ImpotService', () => {
  let service: ImpotService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImpotService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
