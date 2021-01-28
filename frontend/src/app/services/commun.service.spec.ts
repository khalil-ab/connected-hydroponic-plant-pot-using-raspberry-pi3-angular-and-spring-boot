import { TestBed } from '@angular/core/testing';

import { CommunService } from './commun.service';

describe('CommunService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommunService = TestBed.get(CommunService);
    expect(service).toBeTruthy();
  });
});
