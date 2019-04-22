import { TestBed } from '@angular/core/testing';

import { InterceptorService } from './interceptor.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


describe('InterceptorService', () => {

  let interceptorService: InterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [InterceptorService]
    });
    interceptorService = TestBed.get(InterceptorService);
  });

  it('should be created', () => {
//    const service: InterceptorService = TestBed.get(InterceptorService);
    expect(interceptorService).toBeTruthy();
  });
});
