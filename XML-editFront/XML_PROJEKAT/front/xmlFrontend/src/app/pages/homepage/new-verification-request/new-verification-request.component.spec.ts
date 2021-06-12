import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewVerificationRequestComponent } from './new-verification-request.component';

describe('NewVerificationRequestComponent', () => {
  let component: NewVerificationRequestComponent;
  let fixture: ComponentFixture<NewVerificationRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewVerificationRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewVerificationRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
