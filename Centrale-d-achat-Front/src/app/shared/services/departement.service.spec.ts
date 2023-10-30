import { TestBed } from '@angular/core/testing';

import { departementService } from './departement.service';

describe('DepartementService', () => {
  let service: departementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(departementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
