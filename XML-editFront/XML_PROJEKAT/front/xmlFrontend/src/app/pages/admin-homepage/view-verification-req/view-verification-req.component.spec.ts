import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewVerificationReqComponent } from './view-verification-req.component';

describe('ViewVerificationReqComponent', () => {
  let component: ViewVerificationReqComponent;
  let fixture: ComponentFixture<ViewVerificationReqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewVerificationReqComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewVerificationReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
