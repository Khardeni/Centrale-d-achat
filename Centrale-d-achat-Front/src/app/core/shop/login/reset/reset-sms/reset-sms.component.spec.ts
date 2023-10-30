import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetSMSComponent } from './reset-sms.component';

describe('ResetSMSComponent', () => {
  let component: ResetSMSComponent;
  let fixture: ComponentFixture<ResetSMSComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetSMSComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetSMSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
