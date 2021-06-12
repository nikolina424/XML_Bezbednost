import { TestBed } from '@angular/core/testing';

import { VerificationRequestServiceService } from './verification-request-service.service';

describe('VerificationRequestServiceService', () => {
  let service: VerificationRequestServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerificationRequestServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
